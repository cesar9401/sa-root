package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserRequest;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserResponse;
import com.cesar31.root.domain.model.User;
import com.cesar31.root.application.ports.input.UserUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRestAdapter {

    private final UserUseCase userUseCase;

    public UserRestAdapter(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        return userUseCase.findByUserId(id)
                .map(u -> {
                    // TODO: user mapper here
                    var userResponse = new UserResponse();
                    userResponse.setId(u.getUserId());
                    userResponse.setEmail(u.getEmail());
                    userResponse.setEntryDate(u.getEntryDate());
                    return new ResponseEntity<>(userResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("by-email")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam(name = "email") String email) {
        return userUseCase.findByEmail(email)
                .map(u -> {
                    // TODO: user mapper here
                    var userResponse = new UserResponse();
                    userResponse.setId(u.getUserId());
                    userResponse.setEmail(u.getEmail());
                    userResponse.setEntryDate(u.getEntryDate());
                    return new ResponseEntity<>(userResponse, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserRequest userRequest) {
        // TODO: user mapper here
        var user = new User(userRequest.getEmail(), userRequest.getPassword());
        var newUser = userUseCase.createUser(user);

        // TODO: use mapper here
        var response = new UserResponse();
        response.setId(newUser.getUserId());
        response.setEmail(newUser.getEmail());
        response.setEntryDate(newUser.getEntryDate());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
