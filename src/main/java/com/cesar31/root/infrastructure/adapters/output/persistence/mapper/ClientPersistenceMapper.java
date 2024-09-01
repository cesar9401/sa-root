package com.cesar31.root.infrastructure.adapters.output.persistence.mapper;

import com.cesar31.root.domain.Client;
import com.cesar31.root.infrastructure.adapters.output.persistence.entity.ClientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface ClientPersistenceMapper {

    @Mapping(source = "userId", target = "clientId")
    @Mapping(source = "userId", target = "user.userId")
    @Mapping(source = "email", target = "user.email")
    @Mapping(source = "password", target = "user.password")
    @Mapping(source = "firstName", target = "user.firstName")
    @Mapping(source = "lastName", target = "user.lastName")
    @Mapping(source = "birthDate", target = "user.birthDate")
    @Mapping(source = "entryDate", target = "user.entryDate")
    ClientEntity toClientEntity(Client client);
    List<ClientEntity> toClientEntities(List<Client> clients);

    @InheritInverseConfiguration
    Client toClient(ClientEntity clientEntity);
    List<Client> toClients(List<ClientEntity> clientEntities);
}
