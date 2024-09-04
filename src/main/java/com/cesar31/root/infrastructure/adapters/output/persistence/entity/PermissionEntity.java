package com.cesar31.root.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "adm_permission")
public class PermissionEntity {

    @Id
    @Column(name = "permission_id")
    private UUID permissionId;

    @Column(name = "parent_permission_id")
    private UUID parentPermissionId;

    @Column(name = "permission_name")
    private String permissionName;

    @Column(name = "description")
    private String description;
}
