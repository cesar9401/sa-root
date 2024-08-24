package com.cesar31.root.domain.service;

import com.cesar31.root.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    void createUser(User user);
}
