package com.cesar31.root;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("users")
public class AdmUserController {

    private final AdmUserRepository userRepository;

    public AdmUserController(AdmUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String sayHello() {
        return "Hello, there!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdmUser> findById(@PathVariable("id") Long userId) {
        return userRepository.findById(userId)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AdmUser> create(@RequestBody AdmUser user) {
        user.setEntryDate(LocalDateTime.now());
        var userSaved = userRepository.save(user);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }
}
