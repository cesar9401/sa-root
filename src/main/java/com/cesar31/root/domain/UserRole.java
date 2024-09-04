package com.cesar31.root.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRole {

    private UUID userRoleId;
    private User user;
    private Role role;
}
