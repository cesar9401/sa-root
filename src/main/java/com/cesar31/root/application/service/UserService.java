package com.cesar31.root.application.service;

import com.cesar31.root.application.ports.input.UserUseCase;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.application.dto.CreateUserReqDto;
import com.cesar31.root.application.dto.UpdateUserReqDto;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.mapper.UserMapper;
import com.cesar31.root.domain.User;
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
    public User createUser(CreateUserReqDto reqDto) throws ApplicationException {
        // validate
        reqDto.validateSelf();

        var userByEmail = userOutputPort.findByEmail(reqDto.getEmail());
        if (userByEmail.isPresent()) throw new ApplicationException("email_already_exists");
        var user = mapper.toUser(reqDto);
        user.setPassword(passwordEncoderPort.encode(reqDto.getPassword()));
        user.setUserId(UUID.randomUUID());
        user.setEntryDate(LocalDateTime.now());
        return userOutputPort.save(user);
    }

    @Override
    public User updateUser(UUID userId, UpdateUserReqDto reqDto) throws EntityNotFoundException, ApplicationException {
        // validate
        reqDto.validateSelf();

        var userById = userOutputPort.findByUserId(userId);
        if (userById.isEmpty()) throw new EntityNotFoundException("user_not_found");

        var originalUser = userById.get();
        if (!originalUser.getUserId().equals(reqDto.getId())) throw new ApplicationException("invalid_update");

        var userByEmail = userOutputPort.findByEmailAndNotUserId(reqDto.getEmail(), userId);
        if (userByEmail.isPresent()) throw new ApplicationException("email_already_exists");

        var user = mapper.toUser(reqDto);
        user.setPassword(originalUser.getPassword());
        user.setEntryDate(originalUser.getEntryDate());
        return userOutputPort.update(user);
    }
}
