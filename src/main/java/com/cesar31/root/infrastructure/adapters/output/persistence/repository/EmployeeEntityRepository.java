package com.cesar31.root.infrastructure.adapters.output.persistence.repository;

import com.cesar31.root.infrastructure.adapters.output.persistence.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, UUID> {

    List<EmployeeEntity> findAllByOrganizationId(UUID organizationId);

    Optional<EmployeeEntity> findByEmployeeIdAndOrganizationId(UUID userId, UUID organizationId);

    Optional<EmployeeEntity> findByUser_Email(String email);
}
