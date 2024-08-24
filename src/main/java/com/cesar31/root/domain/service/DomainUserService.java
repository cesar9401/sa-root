package com.cesar31.root.domain.service;

import com.cesar31.root.domain.User;
import com.cesar31.root.domain.repository.UserRepository;

import java.util.Optional;

public class DomainUserService implements UserService {

    private final UserRepository userRepository;

    public DomainUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createUser(User user) {
        var userByEmail = userRepository.findByEmail(user.getEmail());
        if (userByEmail.isPresent()) throw new RuntimeException("email_already_exists");
        // TODO: another validations here

        userRepository.save(user);
    }
}
