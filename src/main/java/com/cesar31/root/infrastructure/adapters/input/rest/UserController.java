package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserRequest;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserResponse;
import com.cesar31.root.domain.User;
import com.cesar31.root.application.ports.input.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{email}")
    public ResponseEntity<UserResponse> findByEmail(@PathVariable("email") String email) {
        return userService.findByEmail(email)
                .map(u -> {
                    var userResponse = new UserResponse();
                    userResponse.setEmail(u.getEmail());
                    userResponse.setEntryDate(u.getEntryDate());
                    return new ResponseEntity<>(userResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
       var user = new User(userRequest.getEmail(), userRequest.getPassword());
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
