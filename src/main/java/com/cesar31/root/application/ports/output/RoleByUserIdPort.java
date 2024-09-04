package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.Role;

import java.util.List;
import java.util.UUID;

public interface RoleByUserIdPort {

    List<Role> findRolesByUserId(UUID userId);
}
