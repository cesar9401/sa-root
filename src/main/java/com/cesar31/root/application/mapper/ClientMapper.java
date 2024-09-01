package com.cesar31.root.application.mapper;

import com.cesar31.root.application.dto.CreateClientReqDto;
import com.cesar31.root.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "birthDate", target = "birthDate")
    Client toClient(CreateClientReqDto reqDto);
}
