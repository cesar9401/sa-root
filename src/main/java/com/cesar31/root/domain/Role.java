package com.cesar31.root.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Role {

    private UUID roleId;
    private String name;
    private String description;
}
