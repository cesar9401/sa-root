package com.cesar31.root.application.ports.input;

import com.cesar31.root.application.dto.CreateClientReqDto;
import com.cesar31.root.application.dto.UpdateClientReqDto;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.domain.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientUseCase {

    List<Client> findAll();

    Optional<Client> findById(UUID clientId);

    Client save(CreateClientReqDto createClientReqDto) throws Exception;

    Client update(UUID id, UpdateClientReqDto updateClientReqDto) throws Exception;
}
