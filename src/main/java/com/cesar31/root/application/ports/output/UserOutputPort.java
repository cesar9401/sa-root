package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserOutputPort {

    Optional<User> findByUserId(UUID userId);

    Optional<User> findByEmail(String email);

    User save(User user);
}
