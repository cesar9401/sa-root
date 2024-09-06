package com.cesar31.root.application.service;

import com.cesar31.root.application.ports.input.RolesByUserIdUseCase;
import com.cesar31.root.application.ports.output.RoleByUserIdPort;
import com.cesar31.root.domain.Role;

import java.util.List;
import java.util.UUID;

public class RolesByUserService implements RolesByUserIdUseCase {

    private final RoleByUserIdPort roleByUserIdPort;

    public RolesByUserService(RoleByUserIdPort roleByUserIdPort) {
        this.roleByUserIdPort = roleByUserIdPort;
    }

    @Override
    public List<Role> findRolesByUserId(UUID userId) {
        return roleByUserIdPort.findRolesByUserId(userId);
    }
}
