package com.cesar31.root.infrastructure.adapters.output.persistence.repository;

import com.cesar31.root.infrastructure.adapters.output.persistence.entity.AdmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdmUserRepository extends JpaRepository<AdmUser, UUID> {

    Optional<AdmUser> findByEmail(String email);

    Optional<AdmUser> findByEmailAndUserIdNot(String email, UUID userId);
}
