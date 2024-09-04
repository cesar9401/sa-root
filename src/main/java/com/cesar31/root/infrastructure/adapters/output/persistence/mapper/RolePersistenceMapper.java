package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.Role;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.RoleEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface RolePersistenceMapper {

    @Mapping(source = "roleId", target = "roleId")
    @Mapping(source = "roleName", target = "name")
    @Mapping(source = "description", target = "description")
    Role toRole(RoleEntity entity);
    List<Role> toRoles(List<RoleEntity> entities);

    @InheritInverseConfiguration
    RoleEntity toRoleEntity(Role role);
    List<RoleEntity> toRoleEntities(List<Role> roles);
}
