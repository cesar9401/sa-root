package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.UserEntityRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserByEmailAdapter implements UserByEmailPort {

    private final UserEntityRepository userEntityRepository;
    private final UserPersistenceMapper mapper;

    public UserByEmailAdapter(
            UserEntityRepository userEntityRepository,
            UserPersistenceMapper mapper
    ) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityRepository.findByEmail(email)
                .map(mapper::toUser);
    }
}
