package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.domain.Employee;
import com.cesar31.root.application.ports.output.EmployeeOutputPort;
import com.cesar31.root.domain.UserRole;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.EmployeePersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.UserRolePersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.EmployeeEntityRepository;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.UserEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class EmployeePersistenceAdapter implements EmployeeOutputPort {

    private final EmployeeEntityRepository employeeEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final EmployeePersistenceMapper employeeMapper;
    private final UserRolePersistenceMapper userRoleMapper;

    public EmployeePersistenceAdapter(
            EmployeeEntityRepository employeeEntityRepository,
            UserEntityRepository userEntityRepository,
            EmployeePersistenceMapper employeeMapper,
            UserRolePersistenceMapper userRoleMapper
    ) {
        this.employeeEntityRepository = employeeEntityRepository;
        this.userEntityRepository = userEntityRepository;
        this.employeeMapper = employeeMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Employee> findAll() {
        return employeeEntityRepository.findAll()
                .stream()
                .map(employeeMapper::toEmployee)
                .toList();
    }

    @Override
    public Optional<Employee> findById(UUID userId) {
        return employeeEntityRepository
                .findById(userId)
                .map(employeeMapper::toEmployee);
    }

    @Override
    public Boolean existsByEmail(String email, UUID userId) {
        var optional = userId == null
                ? userEntityRepository.findByEmail(email)
                : userEntityRepository.findByEmailAndUserIdNot(email, userId);

        return optional.isPresent();
    }

    @Override
    public Employee save(Employee employee, List<UserRole> userRoles) {
        var employeeEntity = employeeMapper.toEmployeeEntity(employee);
        var roles = userRoleMapper.toUserRoleEntities(userRoles);

        employeeEntity.getUser().setRoles(roles);
        var createdEmployee = employeeEntityRepository.save(employeeEntity);
        return employeeMapper.toEmployee(createdEmployee);
    }
}
