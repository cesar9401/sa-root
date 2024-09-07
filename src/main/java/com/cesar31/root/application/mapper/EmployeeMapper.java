package com.cesar31.root.application.mapper;

import com.cesar31.root.application.dto.CreateEmployeeReqDto;
import com.cesar31.root.application.dto.UpdateEmployeeReqDto;
import com.cesar31.root.domain.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface EmployeeMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    @Mapping(source = "organizationId", target = "organization")
    Employee toEmployee(CreateEmployeeReqDto reqDto);

    @Mapping(source = "employeeId", target = "userId")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    Employee toEmployee(UpdateEmployeeReqDto reqDto);
}
