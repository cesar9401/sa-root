package com.cesar31.root.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private LocalDateTime entryDate;

    private User() {
        this.entryDate = LocalDateTime.now();
    }

    public User(String email, String password) {
        this();
        this.email = email;
        this.password = password;
    }
}
