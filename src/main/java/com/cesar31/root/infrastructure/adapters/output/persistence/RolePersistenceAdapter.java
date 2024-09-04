package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.application.ports.output.RoleOutputPort;
import com.cesar31.root.domain.Role;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.RolePersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.RoleEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RolePersistenceAdapter implements RoleOutputPort {

    private final RoleEntityRepository roleEntityRepository;
    private final RolePersistenceMapper mapper;

    public RolePersistenceAdapter(RoleEntityRepository roleEntityRepository, RolePersistenceMapper mapper) {
        this.roleEntityRepository = roleEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Role> findAll() {
        return mapper.toRoles(roleEntityRepository.findAll());
    }

    @Override
    public Optional<Role> findById(UUID roleId) {
        return roleEntityRepository.findById(roleId)
                .map(mapper::toRole);
    }
}
