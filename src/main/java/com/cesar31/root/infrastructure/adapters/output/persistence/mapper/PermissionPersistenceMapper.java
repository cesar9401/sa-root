package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.Permission;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.PermissionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface PermissionPersistenceMapper {

    @Mapping(source = "permissionId", target = "permissionId")
    @Mapping(source = "parentPermissionId", target = "parentPermissionId")
    @Mapping(source = "permissionName", target = "name")
    @Mapping(source = "description", target = "description")
    Permission toPermission(PermissionEntity permissionEntity);
    List<Permission> toPermissions(List<PermissionEntity> permissionEntities);
}
