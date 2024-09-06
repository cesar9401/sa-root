package com.cesar31.root.infrastructure.adapters.input.rest.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class SaUser extends User {

    private final UUID userId;

    public SaUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UUID userId) {
        super(username, password, authorities);
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }
}
