package com.cesar31.root.domain.dto;

import com.cesar31.root.domain.util.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdateUserReqDto extends SelfValidating<UpdateUserReqDto> {

    @NotNull
    private UUID id;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
