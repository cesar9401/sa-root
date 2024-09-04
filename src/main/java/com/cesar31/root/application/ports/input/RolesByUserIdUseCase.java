package com.cesar31.root.application.ports.input;

import com.cesar31.root.domain.Role;

import java.util.List;
import java.util.UUID;

public interface RolesByUserIdUseCase {

    List<Role> findRolesByUserId(UUID userId);
}
