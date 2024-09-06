package com.cesar31.root.application.service;

import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import com.cesar31.root.application.ports.output.UserByEmailPort;
import com.cesar31.root.domain.User;

import java.util.Optional;

public class UserByEmailService implements UserByEmailUseCase {

    private final UserByEmailPort userByEmailPort;

    public UserByEmailService(UserByEmailPort userByEmailPort) {
        this.userByEmailPort = userByEmailPort;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userByEmailPort.findByEmail(email);
    }
}
