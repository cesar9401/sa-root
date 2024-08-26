package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.AdmUserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class AdmUserPersistenceAdapter implements UserOutputPort {

    private final AdmUserRepository admUserRepository;
    private final UserPersistenceMapper mapper;

    public AdmUserPersistenceAdapter(AdmUserRepository admUserRepository, UserPersistenceMapper mapper) {
        this.admUserRepository = admUserRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return admUserRepository
                .findById(userId)
                .map(mapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return admUserRepository
                .findByEmail(email)
                .map(mapper::toUser);
    }

    @Override
    public User save(User user) {
        var admUser = mapper.toAdmUser(user);
        var createdUser = admUserRepository.save(admUser);
        return mapper.toUser(createdUser);
    }
}
