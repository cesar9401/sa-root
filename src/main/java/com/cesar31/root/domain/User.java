package com.cesar31.root.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {

    private UUID userId;
    private String email;
    private String password;
    private LocalDateTime entryDate;
}
