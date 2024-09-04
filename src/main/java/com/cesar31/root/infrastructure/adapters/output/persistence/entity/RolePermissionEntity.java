package com.cesar31.root.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "adm_role_permission")
public class RolePermissionEntity {

    @Id
    @Column(name = "role_permission_id")
    private UUID rolePermissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id")
    private PermissionEntity permission;

    @Column(name = "read_permission")
    private Boolean readPermission;

    @Column(name = "create_permission")
    private Boolean createPermission;

    @Column(name = "update_permission")
    private Boolean updatePermission;

    @Column(name = "delete_permission")
    private Boolean deletePermission;
}
