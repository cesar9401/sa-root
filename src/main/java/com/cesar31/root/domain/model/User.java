package com.cesar31.root.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private UUID userId;
    private String email;
    private String password;
    private LocalDateTime entryDate;

    private User() {
        this.entryDate = LocalDateTime.now();
    }
}
