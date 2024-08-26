package com.cesar31.root.infrastructure.adapters.input.rest.security;

import com.cesar31.root.infrastructure.adapters.input.rest.dto.AuthReqDto;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.JwtResDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public JwtResDto createToken(AuthReqDto authReq) {
        var authData = new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword());

        try {
            var authentication = authenticationManager.authenticate(authData);
            if (authentication.isAuthenticated()) {
                var userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
                return jwtService.generateToken(userDetails);
            }
        } catch (Exception e) {
            log.error("Error:", e);
        }

        throw new RuntimeException("invalid_user");
    }
}
