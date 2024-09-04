package com.cesar31.root.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Permission {

    private UUID permissionId;
    private UUID parentPermissionId;
    private String name;
    private String description;
}
