package com.cesar31.root.infrastructure.adapters.input.rest.security;

import com.cesar31.root.application.ports.input.RolesByUserIdUseCase;
import com.cesar31.root.application.ports.input.UserByEmailUseCase;
import com.cesar31.root.domain.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserByEmailUseCase userByEmailUseCase;
    private final RolesByUserIdUseCase rolesByUserIdUseCase;

    public UserDetailsServiceImpl(UserByEmailUseCase userByEmailUseCase, RolesByUserIdUseCase rolesByUserIdUseCase) {
        this.userByEmailUseCase = userByEmailUseCase;
        this.rolesByUserIdUseCase = rolesByUserIdUseCase;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userOpt = userByEmailUseCase.findByEmail(username);
        if (userOpt.isEmpty()) throw new UsernameNotFoundException("user_by_email_not_found");

        var user = userOpt.get();
        var roles = rolesByUserIdUseCase.findRolesByUserId(user.getUserId())
                .stream()
                .map(Role::getRoleId)
                .map(UUID::toString)
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new SaUser(user.getEmail(), user.getPassword(), roles, user.getUserId());
    }
}
