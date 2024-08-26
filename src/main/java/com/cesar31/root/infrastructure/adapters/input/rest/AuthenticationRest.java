package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.infrastructure.adapters.input.rest.dto.AuthReqDto;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.JwtResDto;
import com.cesar31.root.infrastructure.adapters.input.rest.security.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationRest {

    private final AuthenticationService authenticationService;

    public AuthenticationRest(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public ResponseEntity<JwtResDto> createToken(@RequestBody AuthReqDto authReqDto) {
        var token = authenticationService.createToken(authReqDto);
        return ResponseEntity.ok(token);
    }
}
