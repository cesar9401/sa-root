package com.cesar31.root.infrastructure.adapters.output.persistence.repository;

import com.cesar31.root.infrastructure.adapters.output.persistence.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientEntityRepository extends JpaRepository<ClientEntity, UUID> {

}
