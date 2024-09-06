package com.cesar31.root.infrastructure.adapters.input.rest.security;

import com.cesar31.root.application.ports.input.EmployeeUseCase;
import com.cesar31.root.domain.Employee;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.AuthReqDto;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.JwtResDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final EmployeeUseCase employeeUseCase;
    private final JwtService jwtService;

    public AuthenticationService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService, EmployeeUseCase employeeUseCase,
            JwtService jwtService
    ) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.employeeUseCase = employeeUseCase;
        this.jwtService = jwtService;
    }

    public JwtResDto createToken(AuthReqDto authReq) {
        // validate AuthReqDto
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            var validator = factory.getValidator();
            var violations = validator.validate(authReq);
            if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
        }

        var authData = new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword());

        try {
            var authentication = authenticationManager.authenticate(authData);
            if (authentication.isAuthenticated()) {
                var userDetails = userDetailsService.loadUserByUsername(authReq.getUsername());
                var employee = employeeUseCase.findByEmail(userDetails.getUsername());
                var org = employee.map(Employee::getOrganization)
                        .map(UUID::toString)
                        .orElse(null);

                return jwtService.generateToken((SaUser) userDetails, org);
            }
        } catch (Exception e) {
            log.error("Error:", e);
        }

        throw new BadCredentialsException("invalid_credentials");
    }
}
