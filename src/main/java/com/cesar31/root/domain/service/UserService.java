package com.cesar31.root.domain.service;

import com.cesar31.root.application.ports.input.UserUseCase;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.domain.dto.UserReqDto;
import com.cesar31.root.domain.exception.DomainEntityNotFoundException;
import com.cesar31.root.domain.exception.DomainException;
import com.cesar31.root.domain.mapper.UserMapper;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class UserService implements UserUseCase {

    private final UserOutputPort userOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final UserMapper mapper;

    public UserService(UserOutputPort userOutputPort, PasswordEncoderPort passwordEncoderPort, UserMapper mapper) {
        this.userOutputPort = userOutputPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.mapper = mapper;
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
    public User createUser(UserReqDto reqDto) throws DomainException {
        var userByEmail = userOutputPort.findByEmail(reqDto.getEmail());
        if (userByEmail.isPresent()) throw new DomainException("email_already_exists");

        var user = mapper.toUser(reqDto);
        user.setPassword(passwordEncoderPort.encode(reqDto.getPassword()));
        user.setUserId(UUID.randomUUID());
        user.setEntryDate(LocalDateTime.now());
        return userOutputPort.save(user);
    }

    @Override
    public User updateUser(UUID userId, UserReqDto reqDto) throws DomainEntityNotFoundException, DomainException {
        var userById = userOutputPort.findByUserId(userId);
        if (userById.isEmpty()) throw new DomainEntityNotFoundException("user_not_found");

        var originalUser = userById.get();
        if (!originalUser.getUserId().equals(reqDto.getId())) throw new DomainException("invalid_update");

        var userByEmail = userOutputPort.findByEmailAndNotUserId(reqDto.getEmail(), userId);
        if (userByEmail.isPresent()) throw new DomainException("email_already_exists");

        var user = mapper.toUser(reqDto);
        user.setPassword(originalUser.getPassword());
        user.setEntryDate(originalUser.getEntryDate());
        return userOutputPort.update(user);
    }
}
