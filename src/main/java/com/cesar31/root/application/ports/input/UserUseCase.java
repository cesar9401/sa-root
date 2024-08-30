package com.cesar31.root.application.ports.input;

import com.cesar31.root.domain.dto.UserReqDto;
import com.cesar31.root.domain.exception.DomainEntityNotFoundException;
import com.cesar31.root.domain.exception.DomainException;
import com.cesar31.root.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {

    List<User> findAll();

    Optional<User> findByUserId(UUID userId);

    Optional<User> findByEmail(String email);

    User createUser(UserReqDto reqDto) throws DomainException;

    User updateUser(UUID userId, UserReqDto reqDto) throws DomainEntityNotFoundException, DomainException;
}

