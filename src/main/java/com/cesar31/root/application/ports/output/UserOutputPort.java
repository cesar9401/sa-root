package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.model.User;

import java.util.Optional;

public interface UserOutputPort {

    Optional<User> findByEmail(String email);

    void save(User user);
}
