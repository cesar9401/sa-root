package com.cesar31.root.infrastructure.adapters.output.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaAdmUserRepository extends JpaRepository<AdmUser, Long> {

    Optional<AdmUser> findByEmail(String email);
}
