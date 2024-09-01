package com.cesar31.root.application.dto;

import com.cesar31.root.application.util.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class UpdateClientReqDto extends SelfValidating {

    @NotNull
    private UUID clientId;

    @NotBlank
    @NotNull
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotNull
    private LocalDate birthDate;
}
