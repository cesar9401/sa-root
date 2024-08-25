package com.cesar31.root.application.ports.input;

import com.cesar31.root.domain.model.User;

import java.util.Optional;

public interface UserUseCase {

    Optional<User> findByEmail(String email);

    void createUser(User user);
}
