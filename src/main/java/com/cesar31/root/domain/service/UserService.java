package com.cesar31.root.domain.service;

import com.cesar31.root.application.ports.input.UserUseCase;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.domain.exception.DomainEntityNotFoundException;
import com.cesar31.root.domain.exception.DomainException;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements UserUseCase {

    private final UserOutputPort userOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;

    public UserService(UserOutputPort userOutputPort, PasswordEncoderPort passwordEncoderPort) {
        this.userOutputPort = userOutputPort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public List<User> findAll() {
        return userOutputPort.findAll();
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
    public User createUser(User user) throws DomainException {
        var userByEmail = userOutputPort.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new DomainException("email_already_exists");

        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        user.setUserId(UUID.randomUUID());
        user.setEntryDate(LocalDateTime.now());
        return userOutputPort.save(user);
    }

    @Override
    public User updateUser(UUID userId, User user) throws DomainEntityNotFoundException, DomainException {
        var userById = userOutputPort.findByUserId(userId);
        if (userById.isEmpty()) throw new DomainEntityNotFoundException("user_not_found");

        var originalUser = userById.get();
        if (!originalUser.getUserId().equals(user.getUserId())) throw new DomainException("invalid_update");

        var userByEmail = userOutputPort.findByEmailAndNotUserId(user.getEmail(), userId);
        if (userByEmail.isPresent()) throw new DomainException("email_already_exists");

        user.setPassword(originalUser.getPassword());
        user.setEntryDate(originalUser.getEntryDate());
        return userOutputPort.update(user);
    }
}
