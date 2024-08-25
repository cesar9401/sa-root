package com.cesar31.root.infrastructure.adapters.input.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private String email;
    private String password;
}
