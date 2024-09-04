package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.RolePermission;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.RolePermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = { RolePersistenceMapper.class, PermissionPersistenceMapper.class })
public interface RolePermissionPersistenceMapper {

    @Mapping(source = "rolePermissionId", target = "rolePermissionId")
    @Mapping(source = "readPermission", target = "read")
    @Mapping(source = "createPermission", target = "create")
    @Mapping(source = "updatePermission", target = "update")
    @Mapping(source = "deletePermission", target = "delete")
    RolePermission toRolePermission(RolePermissionEntity rolePermissionEntity);
    List<RolePermission> toRolePermissions(List<RolePermissionEntity> rolePermissionEntities);
}
