package com.cesar31.root.application.service;

import com.cesar31.root.application.exception.ForbiddenException;
import com.cesar31.root.application.ports.input.EmployeeUseCase;
import com.cesar31.root.application.ports.output.CurrentUserOutputPort;
import com.cesar31.root.application.ports.output.ExistsOrgOutputPort;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.application.dto.CreateEmployeeReqDto;
import com.cesar31.root.application.dto.UpdateEmployeeReqDto;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.mapper.EmployeeMapper;
import com.cesar31.root.application.ports.output.RoleOutputPort;
import com.cesar31.root.application.util.enums.RoleEnum;
import com.cesar31.root.domain.Employee;
import com.cesar31.root.application.ports.output.EmployeeOutputPort;
import com.cesar31.root.domain.UserRole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class EmployeeService implements EmployeeUseCase {

    private final EmployeeOutputPort employeeOutputPort;
    private final RoleOutputPort roleOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final EmployeeMapper mapper;
    private final CurrentUserOutputPort currentUserOutputPort;
    private final ExistsOrgOutputPort existsOrgOutputPort;

    private final Set<UUID> allowedRoles = Set.of(RoleEnum.EMPLOYEE.roleId, RoleEnum.ROOT.roleId);

    public EmployeeService(
            EmployeeOutputPort employeeOutputPort,
            RoleOutputPort roleOutputPort,
            PasswordEncoderPort passwordEncoderPort,
            EmployeeMapper mapper,
            CurrentUserOutputPort currentUserOutputPort, ExistsOrgOutputPort existsOrgOutputPort
    ) {
        this.employeeOutputPort = employeeOutputPort;
        this.roleOutputPort = roleOutputPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.mapper = mapper;
        this.currentUserOutputPort = currentUserOutputPort;
        this.existsOrgOutputPort = existsOrgOutputPort;
    }

    @Override
    public List<Employee> findAll() {
        return employeeOutputPort.findAll(currentUserOutputPort.getOrganizationId());
    }

    @Override
    public Optional<Employee> findByUserId(UUID userId) {
        return employeeOutputPort.findByIdAndOrganizationId(userId, currentUserOutputPort.getOrganizationId());
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeOutputPort.findByEmail(email);
    }

    @Override
    public Employee createEmployee(CreateEmployeeReqDto reqDto) throws Exception {
        // validate
        reqDto.validateSelf();

        // test if forbidden
        testForbidden(reqDto.getRoles());

        var userByEmail = employeeOutputPort.existsByEmail(reqDto.getEmail(), null);
        if (userByEmail) throw new ApplicationException("email_already_exists");

        var user = mapper.toEmployee(reqDto);
        user.setPassword(passwordEncoderPort.encode(reqDto.getPassword()));
        user.setUserId(UUID.randomUUID());
        user.setEntryDate(LocalDateTime.now());

        var orgId = reqDto.getOrganizationId();
        var defaultOrg = currentUserOutputPort.getOrganizationId();

        if (orgId != null && orgId.compareTo(defaultOrg) != 0) {
            var isRoot = currentUserOutputPort.hasRole(RoleEnum.ROOT.roleId);
            if (!isRoot) throw new ForbiddenException("not_allowed_to_select_org");

            var existsOrg = existsOrgOutputPort.existsOrganizationById(orgId);
            if (!existsOrg) throw new EntityNotFoundException("organization_not_found");

            user.setOrganization(orgId);
        } else {
            user.setOrganization(defaultOrg);
        }

        var employeeRoles = getDefaultRoles(user, reqDto.getRoles());
        return employeeOutputPort.save(user, new ArrayList<>(employeeRoles));
    }

    @Override
    public Employee updateEmployee(UUID userId, UpdateEmployeeReqDto reqDto) throws Exception {
        // validate
        reqDto.validateSelf();

        // test if forbidden
        testForbidden(reqDto.getRoles());

        var userById = this.findByUserId(userId);
        if (userById.isEmpty()) throw new EntityNotFoundException("user_not_found");

        var originalUser = userById.get();
        if (!originalUser.getUserId().equals(reqDto.getEmployeeId())) throw new ApplicationException("invalid_update");

        var userByEmail = employeeOutputPort.existsByEmail(reqDto.getEmail(), userId);
        if (userByEmail) throw new ApplicationException("email_already_exists");

        var user = mapper.toEmployee(reqDto);
        user.setPassword(originalUser.getPassword());
        user.setEntryDate(originalUser.getEntryDate());
        user.setOrganization(originalUser.getOrganization());

        var employeeRoles = getDefaultRoles(user, reqDto.getRoles());
        return employeeOutputPort.save(user, employeeRoles);
    }

    private void testForbidden(Set<UUID> roles) throws ForbiddenException {
        var isEmployeeOrRoot = currentUserOutputPort.hasAnyRole(allowedRoles);
        if (!isEmployeeOrRoot) throw new ForbiddenException("not_allowed_to_update_employee");

        var isRoot = currentUserOutputPort.hasRole(RoleEnum.ROOT.roleId);
        if (roles.contains(RoleEnum.ROOT.roleId) && !isRoot)
            throw new ForbiddenException("not_allowed_to_add_root_role");

        var isRestManager = currentUserOutputPort.hasRole(RoleEnum.RESTAURANT_MANAGER.roleId);
        if (roles.contains(RoleEnum.RESTAURANT_MANAGER.roleId) && !isRoot && !isRestManager)
            throw new ForbiddenException("not_allowed_to_add_restaurant_role");

        var isHotelManager = currentUserOutputPort.hasRole(RoleEnum.HOTEL_MANAGER.roleId);
        if (roles.contains(RoleEnum.HOTEL_MANAGER.roleId) && !isRoot && !isHotelManager)
            throw new ForbiddenException("not_allowed_to_add_hotel_role");
    }

    private List<UserRole> getDefaultRoles(Employee employee, Set<UUID> roles) {
        roles.add(RoleEnum.EMPLOYEE.roleId);
        return roleOutputPort.findAll()
                .stream()
                .filter(role -> roles.contains(role.getRoleId()))
                .map(role -> {
                    var userRole = new UserRole();
                    userRole.setUserRoleId(UUID.randomUUID());
                    userRole.setUser(employee);
                    userRole.setRole(role);
                    return userRole;
                })
                .toList();
    }
}
