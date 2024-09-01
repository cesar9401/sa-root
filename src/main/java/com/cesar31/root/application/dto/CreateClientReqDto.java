package com.cesar31.root.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateClientReqDto {

    private String email;
    private String password;
    private String cui;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
}
