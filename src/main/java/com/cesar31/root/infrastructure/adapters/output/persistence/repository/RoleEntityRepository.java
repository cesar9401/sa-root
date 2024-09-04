package com.cesar31.root.infrastructure.adapters.output.persistence.repository;

import com.cesar31.root.infrastructure.adapters.output.persistence.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, UUID> {

    @Query(value = "SELECT role FROM UserRoleEntity userRole INNER JOIN userRole.role role WHERE userRole.user.userId = :userId")
    List<RoleEntity> findAllRolesByUserId(@Param("userId") UUID userId);
}
