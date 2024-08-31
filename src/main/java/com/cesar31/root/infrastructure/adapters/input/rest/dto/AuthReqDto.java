package com.cesar31.root.infrastructure.adapters.input.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthReqDto {

    @NotNull
    @NotBlank
    @Email
    private String username;

    @NotNull
    @NotBlank
    private String password;
}
