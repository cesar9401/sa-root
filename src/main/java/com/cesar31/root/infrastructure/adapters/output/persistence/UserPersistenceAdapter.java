package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.UserPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.UserEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserPersistenceAdapter implements UserOutputPort {

    private final UserEntityRepository userEntityRepository;
    private final UserPersistenceMapper mapper;

    public UserPersistenceAdapter(
            UserEntityRepository userEntityRepository,
            UserPersistenceMapper mapper
    ) {
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAll() {
        return userEntityRepository.findAll()
                .stream()
                .map(mapper::toUser)
                .toList();
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return userEntityRepository
                .findById(userId)
                .map(mapper::toUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userEntityRepository
                .findByEmail(email)
                .map(mapper::toUser);
    }

    @Override
    public Optional<User> findByEmailAndNotUserId(String email, UUID userId) {
        return userEntityRepository
                .findByEmailAndUserIdNot(email, userId)
                .map(mapper::toUser);
    }

    @Override
    public User save(User user) {
        var admUser = mapper.toUserEntity(user);
        var createdUser = userEntityRepository.save(admUser);
        return mapper.toUser(createdUser);
    }

    @Override
    public User update(User user) {
        var admUser = mapper.toUserEntity(user);
        var updatedUser = userEntityRepository.save(admUser);
        return mapper.toUser(updatedUser);
    }
}
