package com.cesar31.root.application.ports.input;

import com.cesar31.root.application.dto.CreateEmployeeReqDto;
import com.cesar31.root.application.dto.UpdateEmployeeReqDto;
import com.cesar31.root.domain.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeUseCase {

    List<Employee> findAll() throws Exception;

    Optional<Employee> findByUserId(UUID userId) throws Exception;

    Optional<Employee> findByEmail(String email) throws Exception;

    Employee createEmployee(CreateEmployeeReqDto reqDto) throws Exception;

    Employee updateEmployee(UUID userId, UpdateEmployeeReqDto reqDto) throws Exception;
}
