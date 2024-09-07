package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.application.dto.CreateEmployeeReqDto;
import com.cesar31.root.application.dto.UpdateEmployeeReqDto;
import com.cesar31.root.domain.Employee;
import com.cesar31.root.application.ports.input.EmployeeUseCase;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employees")
public class EmployeeRestAdapter {

    private final EmployeeUseCase employeeUseCase;

    public EmployeeRestAdapter(EmployeeUseCase employeeUseCase) {
        this.employeeUseCase = employeeUseCase;
    }

    @GetMapping
    @Operation(description = "Find all employees.")
    public ResponseEntity<List<Employee>> findAll() throws Exception {
        List<Employee> employees = employeeUseCase.findAll();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("{userId}")
    @Operation(description = "Get any employee by id.")
    public ResponseEntity<Employee> getUser(@PathVariable("userId") UUID id) throws Exception {
        return employeeUseCase.findByUserId(id)
                .map(ResponseEntity::ok)
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(description = "Create a new user.")
    public ResponseEntity<Employee> create(@RequestBody CreateEmployeeReqDto reqDto) throws Exception {
        var newUser = employeeUseCase.createEmployee(reqDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    @Operation(description = "Update any user by its id.")
    public ResponseEntity<Employee> update(@PathVariable("userId") UUID userId, @RequestBody UpdateEmployeeReqDto reqDto) throws Exception {
        var updatedUser = employeeUseCase.updateEmployee(userId, reqDto);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
