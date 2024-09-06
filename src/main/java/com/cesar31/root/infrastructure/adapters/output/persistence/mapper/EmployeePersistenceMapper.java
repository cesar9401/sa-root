package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.Employee;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.EmployeeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EmployeePersistenceMapper {

    @Mapping(source = "userId", target = "employeeId")
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "birthDate", target = "user.birthDate")
    @Mapping(source = "entryDate", target = "user.entryDate")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "organization", target = "organizationId")
    EmployeeEntity toEmployeeEntity(Employee employee);
    List<EmployeeEntity> toEmployeeEntityList(List<Employee> employees);

    @InheritInverseConfiguration
    Employee toEmployee(EmployeeEntity employeeEntity);
    List<Employee> toEmployees(List<EmployeeEntity> employeeEntities);
}
