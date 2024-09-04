package com.cesar31.root.application.service;

import com.cesar31.root.application.ports.input.RolesByUserIdUseCase;
import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import com.cesar31.root.application.ports.output.RoleByUserIdPort;
import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.domain.Role;
import com.cesar31.root.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserByEmailService implements UserByEmailUseCase, RolesByUserIdUseCase {

    private final UserByEmailPort userByEmailPort;
    private final RoleByUserIdPort roleByUserIdPort;

    public UserByEmailService(UserByEmailPort userByEmailPort, RoleByUserIdPort roleByUserIdPort) {
        this.userByEmailPort = userByEmailPort;
        this.roleByUserIdPort = roleByUserIdPort;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userByEmailPort.findByEmail(email);
    }

    @Override
    public List<Role> findRolesByUserId(UUID userId) {
        return roleByUserIdPort.findRolesByUserId(userId);
    }
}
