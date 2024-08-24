package com.cesar31.root.domain.repository;

import com.cesar31.root.domain.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    void save(User user);
}
