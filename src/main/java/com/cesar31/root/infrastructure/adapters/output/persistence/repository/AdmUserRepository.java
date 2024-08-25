package com.cesar31.root.infrastructure.adapters.output.persistence.repository;

import com.cesar31.root.infrastructure.adapters.output.persistence.entity.AdmUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmUserRepository extends JpaRepository<AdmUser, Long> {

    Optional<AdmUser> findByEmail(String email);
}
