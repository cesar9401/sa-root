package com.cesar31.root.application.service;

import com.cesar31.root.application.ports.input.RoleUseCase;
import com.cesar31.root.application.ports.output.RoleOutputPort;
import com.cesar31.root.domain.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RoleService implements RoleUseCase {

    private final RoleOutputPort roleOutputPort;

    public RoleService(RoleOutputPort roleOutputPort) {
        this.roleOutputPort = roleOutputPort;
    }

    @Override
    public List<Role> findAll() {
        return roleOutputPort.findAll();
    }

    @Override
    public Optional<Role> findById(UUID roleId) {
        return roleOutputPort.findById(roleId);
    }
}
