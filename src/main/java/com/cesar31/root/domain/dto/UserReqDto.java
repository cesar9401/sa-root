package com.cesar31.root.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserReqDto {

    private UUID id;
    private String email;
    private String password;
}
