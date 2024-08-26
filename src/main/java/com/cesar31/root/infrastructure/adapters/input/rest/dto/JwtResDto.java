package com.cesar31.root.infrastructure.adapters.input.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResDto {

    private String token;
    private Long expiresIn;
}
