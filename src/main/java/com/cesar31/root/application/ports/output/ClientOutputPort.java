package com.cesar31.root.application.ports.output;

import com.cesar31.root.domain.Client;
import com.cesar31.root.domain.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientOutputPort {

    List<Client> findAll();

    Optional<Client> findById(UUID id);

    Boolean existsByEmail(String email, UUID userId);

    Client save(Client client, List<UserRole> roles);
}
