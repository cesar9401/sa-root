package com.cesar31.root.domain.service;

import com.cesar31.root.application.ports.input.UserUseCase;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.output.UserOutputPort;

import java.util.Optional;

public class UserService implements UserUseCase {

    private final UserOutputPort userOutputPort;

    public UserService(UserOutputPort userOutputPort) {
        this.userOutputPort = userOutputPort;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userOutputPort.findByEmail(email);
    }

    @Override
    public void createUser(User user) {
        var userByEmail = userOutputPort.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new RuntimeException("email_already_exists");
        // TODO: another validations here

        userOutputPort.save(user);
    }
}
