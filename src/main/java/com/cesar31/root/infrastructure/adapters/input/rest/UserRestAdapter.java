package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.domain.dto.UserReqDto;
import com.cesar31.root.domain.exception.DomainEntityNotFoundException;
import com.cesar31.root.domain.exception.DomainException;
import com.cesar31.root.infrastructure.adapters.input.rest.dto.UserResponse;
import com.cesar31.root.application.ports.input.UserUseCase;
import com.cesar31.root.infrastructure.adapters.input.rest.mapper.UserRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserRestAdapter {

    private final UserUseCase userUseCase;
    private final UserRestMapper mapper;

    public UserRestAdapter(UserUseCase userUseCase, UserRestMapper mapper) {
        this.userUseCase = userUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        var users = userUseCase.findAll()
                .stream()
                .map(mapper::toUserResponse)
                .toList();

        return ResponseEntity.ok(users);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") UUID id) {
        return userUseCase.findByUserId(id)
                .map(u -> ResponseEntity.ok(mapper.toUserResponse(u)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("by-email")
    public ResponseEntity<UserResponse> findByEmail(@RequestParam(name = "email") String email) {
        return userUseCase.findByEmail(email)
                .map(u -> ResponseEntity.ok(mapper.toUserResponse(u)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody UserReqDto reqDto) throws DomainException {
        var newUser = userUseCase.createUser(reqDto);
        return new ResponseEntity<>(mapper.toUserResponse(newUser), HttpStatus.CREATED);
    }

    @PutMapping("{userId}")
    public ResponseEntity<UserResponse> update(@PathVariable("userId") UUID userId, @RequestBody UserReqDto reqDto) throws DomainEntityNotFoundException, DomainException {
        var updatedUser = userUseCase.updateUser(userId, reqDto);
        return new ResponseEntity<>(mapper.toUserResponse(updatedUser), HttpStatus.OK);
    }
}
