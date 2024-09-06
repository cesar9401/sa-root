package com.cesar31.root.application.service;

import com.cesar31.root.application.ports.input.EmployeeUseCase;
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
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class EmployeeService implements EmployeeUseCase {

    private final EmployeeOutputPort employeeOutputPort;
    private final RoleOutputPort roleOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final EmployeeMapper mapper;

    public EmployeeService(EmployeeOutputPort employeeOutputPort, RoleOutputPort roleOutputPort, PasswordEncoderPort passwordEncoderPort, EmployeeMapper mapper) {
        this.employeeOutputPort = employeeOutputPort;
        this.roleOutputPort = roleOutputPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.mapper = mapper;
    }

    @Override
    public List<Employee> findAll() {
        return employeeOutputPort.findAll();
    }

    @Override
    public Optional<Employee> findByUserId(UUID userId) {
        return employeeOutputPort.findById(userId);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return employeeOutputPort.findByEmail(email);
    }

    @Override
    public Employee createEmployee(CreateEmployeeReqDto reqDto) throws ApplicationException {
        // validate
        reqDto.validateSelf();

        var userByEmail = employeeOutputPort.existsByEmail(reqDto.getEmail(), null);
        if (userByEmail) throw new ApplicationException("email_already_exists");
        var user = mapper.toEmployee(reqDto);
        user.setPassword(passwordEncoderPort.encode(reqDto.getPassword()));
        user.setUserId(UUID.randomUUID());
        user.setEntryDate(LocalDateTime.now());

        var employeeRoles = getDefaultRoles(user, reqDto.getRoles());
        return employeeOutputPort.save(user, employeeRoles);
    }

    @Override
    public Employee updateEmployee(UUID userId, UpdateEmployeeReqDto reqDto) throws EntityNotFoundException, ApplicationException {
        // validate
        reqDto.validateSelf();

        var userById = employeeOutputPort.findById(userId);
        if (userById.isEmpty()) throw new EntityNotFoundException("user_not_found");

        var originalUser = userById.get();
        if (!originalUser.getUserId().equals(reqDto.getEmployeeId())) throw new ApplicationException("invalid_update");

        var userByEmail = employeeOutputPort.existsByEmail(reqDto.getEmail(), userId);
        if (userByEmail) throw new ApplicationException("email_already_exists");

        var user = mapper.toEmployee(reqDto);
        user.setPassword(originalUser.getPassword());
        user.setEntryDate(originalUser.getEntryDate());

        var employeeRoles = getDefaultRoles(user, reqDto.getRoles());
        return employeeOutputPort.save(user, employeeRoles);
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
