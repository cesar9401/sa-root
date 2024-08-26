package com.cesar31.root.infrastructure.adapters.input.rest.mapper;

import com.cesar31.root.domain.model.User;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserRequest;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserRestMapper {

    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    User toUser(UserRequest userRequest);

    @Mapping(source = "userId", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "entryDate", target = "entryDate")
    UserResponse toUserResponse(User user);
}
