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
@Table(name = "adm_role")
public class RoleEntity {

    @Id
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "name")
    private String roleName;

    @Column(name = "description")
    private String description;
}
