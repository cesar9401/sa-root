package com.cesar31.root.application.ports.input;

import com.cesar31.root.domain.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoleUseCase {

    List<Role> findAll();

    Optional<Role> findById(UUID roleId);
}
