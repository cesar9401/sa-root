package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.application.ports.output.ClientOutputPort;
import com.cesar31.root.domain.Client;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.ClientPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.ClientEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ClientPersistenceAdapter implements ClientOutputPort {

    private final ClientEntityRepository clientEntityRepository;
    private final ClientPersistenceMapper mapper;

    public ClientPersistenceAdapter(ClientEntityRepository clientEntityRepository, ClientPersistenceMapper mapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Client> findAll() {
        var clients = clientEntityRepository.findAll();
        return mapper.toClients(clients);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return clientEntityRepository.findById(id)
                .map(mapper::toClient);
    }

    @Override
    public Optional<Client> findDuplicatedEmail(String email, UUID personId) {
        return clientEntityRepository.findByUser_EmailAndClientIdNot(email, personId)
                .map(mapper::toClient);
    }

    @Override
    public Client save(Client client) {
        var clientEntity = mapper.toClientEntity(client);
        var persistedClient = clientEntityRepository.save(clientEntity);
        return mapper.toClient(persistedClient);
    }
}
