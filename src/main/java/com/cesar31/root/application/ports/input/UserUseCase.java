package com.cesar31.root.application.ports.input;

import com.cesar31.root.application.dto.CreateUserReqDto;
import com.cesar31.root.application.dto.UpdateUserReqDto;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserUseCase {

    List<User> findAll();

    Optional<User> findByUserId(UUID userId);

    Optional<User> findByEmail(String email);

    User createUser(CreateUserReqDto reqDto) throws ApplicationException;

    User updateUser(UUID userId, UpdateUserReqDto reqDto) throws EntityNotFoundException, ApplicationException;
}
