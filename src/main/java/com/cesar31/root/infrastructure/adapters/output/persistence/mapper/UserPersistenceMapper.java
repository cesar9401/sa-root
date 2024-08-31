package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.User;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserPersistenceMapper {

    User toUser(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
