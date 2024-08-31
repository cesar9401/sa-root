package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.User;

import java.util.Optional;

public interface UserByEmailPort {

    Optional<User> findByEmail(String email);
}
