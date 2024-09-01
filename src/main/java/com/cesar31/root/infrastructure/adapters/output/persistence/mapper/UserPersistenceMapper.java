package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.User;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserPersistenceMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "entryDate", target = "entryDate")
    User toUser(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity toUserEntity(User user);
}
