package com.cesar31.root.domain.service;

import com.cesar31.root.application.ports.input.UserUseCase;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class UserService implements UserUseCase {

    private final UserOutputPort userOutputPort;

    public UserService(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public Optional<User> findByUserId(UUID userId) {
        return userOutputPort.findByUserId(userId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userOutputPort.findByEmail(email);
    }

    @Override
    public User createUser(User user) {
        var userByEmail = userOutputPort.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new RuntimeException("email_already_exists");

        user.setUserId(UUID.randomUUID());
        user.setEntryDate(LocalDateTime.now());
        return userOutputPort.save(user);
    }

    @Override
    public User updateUser(UUID userId, User user) {
        var userById = userOutputPort.findByUserId(userId);
        if (userById.isEmpty()) throw new RuntimeException("user_not_found");

        var originalUser = userById.get();
        if (!originalUser.getUserId().equals(user.getUserId())) throw new RuntimeException("invalid_update");

        var userByEmail = userOutputPort.findByEmailAndNotUserId(user.getEmail(), userId);
        if (userByEmail.isPresent()) throw new RuntimeException("email_already_exists");
        return userOutputPort.update(user);
    }
}
