package com.cesar31.root.infrastructure.adapters.input.rest.security;

import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserByEmailUseCase userByEmailUseCase;

    public UserDetailsServiceImpl(UserByEmailUseCase userByEmailUseCase) {
        this.userByEmailUseCase = userByEmailUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOpt = userByEmailUseCase.findByEmail(username);
        if (userOpt.isEmpty()) throw new UsernameNotFoundException("user_by_email_not_found");

        var user = userOpt.get();
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
