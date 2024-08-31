package com.cesar31.root.application.ports.input;

import com.cesar31.root.domain.User;

import java.util.Optional;

public interface UserByEmailUseCase {

    Optional<User> findByEmail(String email);
}
