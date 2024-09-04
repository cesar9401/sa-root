package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.application.ports.output.RoleByUserIdPort;
import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.domain.Role;
import com.cesar31.root.domain.User;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.RolePersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.RoleEntityRepository;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.UserEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserAndRoleAdapter implements UserByEmailPort, RoleByUserIdPort {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final UserPersistenceMapper userMapper;
    private final RolePersistenceMapper roleMapper;

    public UserAndRoleAdapter(
            UserEntityRepository userEntityRepository, RoleEntityRepository roleEntityRepository,
            UserPersistenceMapper userMapper, RolePersistenceMapper roleMapper
    ) {
        this.userEntityRepository = userEntityRepository;
        this.roleEntityRepository = roleEntityRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(userMapper::toUser);
    }

    @Override
    public List<Role> findRolesByUserId(UUID userId) {
        var roles = roleEntityRepository.findAllRolesByUserId(userId);
        return roleMapper.toRoles(roles);
    }
}
