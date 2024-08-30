package com.cesar31.root.domain.mapper;

import com.cesar31.root.domain.dto.UserReqDto;
import com.cesar31.root.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "id", target = "userId")
    User toUser(UserReqDto reqDto);
}
