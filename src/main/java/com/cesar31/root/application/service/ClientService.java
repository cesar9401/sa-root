package com.cesar31.root.application.service;

import com.cesar31.root.application.dto.CreateClientReqDto;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.mapper.ClientMapper;
import com.cesar31.root.application.ports.input.ClientUseCase;
import com.cesar31.root.application.ports.output.ClientOutputPort;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.domain.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService implements ClientUseCase {

    private final ClientOutputPort clientOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final ClientMapper clientMapper;

    public ClientService(ClientOutputPort clientOutputPort, PasswordEncoderPort passwordEncoderPort, ClientMapper clientMapper) {
        this.clientOutputPort = clientOutputPort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.clientMapper = clientMapper;
    }

    @Override
    public List<Client> findAll() {
        return clientOutputPort.findAll();
    }

    @Override
    public Optional<Client> findById(UUID clientId) {
        return clientOutputPort.findById(clientId);
    }

    @Override
    public Client save(CreateClientReqDto createClientReqDto) throws ApplicationException {
        var client = clientMapper.toClient(createClientReqDto);

        var existsByEmail = clientOutputPort.existsByEmail(client.getEmail(), null);
        if (existsByEmail) throw new ApplicationException("email_already_exists");

        client.setUserId(UUID.randomUUID());
        client.setEntryDate(LocalDateTime.now());
        client.setPassword(passwordEncoderPort.encode(createClientReqDto.getPassword()));
        return clientOutputPort.save(client);
    }
}
