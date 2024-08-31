package com.cesar31.root.application.dto;

import com.cesar31.root.application.util.SelfValidating;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserReqDto extends SelfValidating<CreateUserReqDto> {

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @NotNull
    private String password;
}
