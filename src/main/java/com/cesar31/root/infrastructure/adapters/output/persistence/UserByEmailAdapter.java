package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.AdmUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserByEmailAdapter implements UserByEmailPort {

    private final AdmUserRepository admUserRepository;
    private final UserPersistenceMapper mapper;

    public UserByEmailAdapter(
            AdmUserRepository admUserRepository,
            UserPersistenceMapper mapper
    ) {
        this.admUserRepository = admUserRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return admUserRepository.findByEmail(email)
                .map(mapper::toUser);
    }
}
