package com.cesar31.root.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RolePermission {

    private UUID rolePermissionId;
    private Role role;
    private Permission permission;
    private Boolean read;
    private Boolean create;
    private Boolean update;
    private Boolean delete;
}
