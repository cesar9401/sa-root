package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.UserRole;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.UserRoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = { RolePersistenceMapper.class, UserPersistenceMapper.class })
public interface UserRolePersistenceMapper {

    @Mapping(source = "userRoleId", target = "userRoleId")
    UserRoleEntity toUserRoleEntity(UserRole userRole);
    List<UserRoleEntity> toUserRoleEntities(List<UserRole> userRoles);
}
