package com.cesar31.root.infrastructure.adapters.input.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserRequest {

    private UUID id;
    private String email;
    private String password;
}
