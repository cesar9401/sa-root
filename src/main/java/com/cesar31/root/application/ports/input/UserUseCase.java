package com.cesar31.root.application.ports.input;

import com.cesar31.root.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {

    Optional<User> findByUserId(UUID userId);

    Optional<User> findByEmail(String email);

    User createUser(User user);

    User updateUser(UUID userId, User user);
}
