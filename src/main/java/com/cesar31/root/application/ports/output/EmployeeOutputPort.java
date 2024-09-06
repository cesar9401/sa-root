package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.Employee;
import com.cesar31.root.domain.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeOutputPort {

    List<Employee> findAll();

    Optional<Employee> findById(UUID userId);

    Boolean existsByEmail(String email, UUID userId);

    Optional<Employee> findByEmail(String email);

    Employee save(Employee user, List<UserRole> userRoles);
}
