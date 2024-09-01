package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeOutputPort {

    List<Employee> findAll();

    Optional<Employee> findById(UUID userId);

    Boolean existsByEmail(String email, UUID userId);

    Employee save(Employee user);

    Employee update(Employee user);
}
