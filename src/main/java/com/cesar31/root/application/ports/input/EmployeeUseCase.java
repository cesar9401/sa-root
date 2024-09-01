package com.cesar31.root.application.ports.input;

import com.cesar31.root.application.dto.CreateEmployeeReqDto;
import com.cesar31.root.application.dto.UpdateEmployeeReqDto;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.domain.Employee;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeUseCase {

    List<Employee> findAll();

    Optional<Employee> findByUserId(UUID userId);

    Employee createEmployee(CreateEmployeeReqDto reqDto) throws ApplicationException;

    Employee updateEmployee(UUID userId, UpdateEmployeeReqDto reqDto) throws EntityNotFoundException, ApplicationException;
}
