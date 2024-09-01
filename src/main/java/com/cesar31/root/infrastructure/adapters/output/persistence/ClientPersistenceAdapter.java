package com.cesar31.root.infrastructure.adapters.output.persistence;

import com.cesar31.root.application.ports.output.ClientOutputPort;
import com.cesar31.root.domain.Client;
import com.cesar31.root.infrastructure.adapters.output.persistence.mapper.ClientPersistenceMapper;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.ClientEntityRepository;
import com.cesar31.root.infrastructure.adapters.output.persistence.repository.UserEntityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ClientPersistenceAdapter implements ClientOutputPort {

    private final ClientEntityRepository clientEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final ClientPersistenceMapper mapper;

    public ClientPersistenceAdapter(ClientEntityRepository clientEntityRepository, UserEntityRepository userEntityRepository, ClientPersistenceMapper mapper) {
        this.clientEntityRepository = clientEntityRepository;
        this.userEntityRepository = userEntityRepository;
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
    public Boolean existsByEmail(String email, UUID userId) {
        var optional = userId == null
                ? userEntityRepository.findByEmail(email)
                : userEntityRepository.findByEmailAndUserIdNot(email, userId);

        return optional.isPresent();
    }

    @Override
    public Client save(Client client) {
        var clientEntity = mapper.toClientEntity(client);
        var persistedClient = clientEntityRepository.save(clientEntity);
        return mapper.toClient(persistedClient);
    }
}
