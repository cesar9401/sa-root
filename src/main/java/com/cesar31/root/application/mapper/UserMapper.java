package com.cesar31.root.application.mapper;

import com.cesar31.root.application.dto.CreateUserReqDto;
import com.cesar31.root.application.dto.UpdateUserReqDto;
import com.cesar31.root.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toUser(CreateUserReqDto reqDto);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "id", target = "userId")
    User toUser(UpdateUserReqDto reqDto);
}
