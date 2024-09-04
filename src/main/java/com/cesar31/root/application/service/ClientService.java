package com.cesar31.root.application.service;

import com.cesar31.root.application.dto.CreateClientReqDto;
import com.cesar31.root.application.dto.UpdateClientReqDto;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.application.mapper.ClientMapper;
import com.cesar31.root.application.ports.input.ClientUseCase;
import com.cesar31.root.application.ports.output.ClientOutputPort;
import com.cesar31.root.application.ports.output.PasswordEncoderPort;
import com.cesar31.root.application.ports.output.RoleOutputPort;
import com.cesar31.root.application.util.enums.RoleEnum;
import com.cesar31.root.domain.Client;
import com.cesar31.root.domain.UserRole;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService implements ClientUseCase {

    private final ClientOutputPort clientOutputPort;
    private final RoleOutputPort roleOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final ClientMapper clientMapper;

    public ClientService(ClientOutputPort clientOutputPort, RoleOutputPort roleOutputPort, PasswordEncoderPort passwordEncoderPort, ClientMapper clientMapper) {
        this.clientOutputPort = clientOutputPort;
        this.roleOutputPort = roleOutputPort;
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
        // validate
        createClientReqDto.validateSelf();

        var client = clientMapper.toClient(createClientReqDto);

        var existsByEmail = clientOutputPort.existsByEmail(client.getEmail(), null);
        if (existsByEmail) throw new ApplicationException("email_already_exists");

        client.setUserId(UUID.randomUUID());
        client.setEntryDate(LocalDateTime.now());
        client.setPassword(passwordEncoderPort.encode(createClientReqDto.getPassword()));

        var clientRoles = getDefaultRoles(client);
        return clientOutputPort.save(client, clientRoles);
    }

    @Override
    public Client update(UUID clientId, UpdateClientReqDto updateClientReqDto) throws ApplicationException, EntityNotFoundException {
        // validate
        updateClientReqDto.validateSelf();

        var clientById = findById(clientId);
        if (clientById.isEmpty()) throw new EntityNotFoundException("client_not_found");

        var originalClient = clientById.get();
        if (!originalClient.getUserId().equals(updateClientReqDto.getClientId()))
            throw new ApplicationException("invalid_update");

        var duplicatedEmail = clientOutputPort.existsByEmail(updateClientReqDto.getEmail(), clientId);
        if (duplicatedEmail) throw new ApplicationException("email_already_exists");

        var client = clientMapper.toClient(updateClientReqDto);
        client.setPassword(originalClient.getPassword());
        client.setEntryDate(originalClient.getEntryDate());

        var clientRoles = getDefaultRoles(client);
        return clientOutputPort.save(client, clientRoles);
    }

    private List<UserRole> getDefaultRoles(Client client) throws ApplicationException {
        var clientRole = roleOutputPort.findById(RoleEnum.CLIENT.roleId)
                .map(role -> {
                    var userRole = new UserRole();
                    userRole.setUserRoleId(UUID.randomUUID());
                    userRole.setUser(client);
                    userRole.setRole(role);
                    return userRole;
                })
                .orElseThrow(() -> new ApplicationException("client_role_not_found"));

        return List.of(clientRole);
    }
}
