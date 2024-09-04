package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.application.ports.input.RoleUseCase;
import com.cesar31.root.domain.Role;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("roles")
public class RoleRestAdapter {

    private final RoleUseCase roleUseCase;

    public RoleRestAdapter(RoleUseCase roleUseCase) {
        this.roleUseCase = roleUseCase;
    }

    @GetMapping
    @Operation(description = "Find all roles.")
    public ResponseEntity<List<Role>> findAll() {
        var roles = roleUseCase.findAll();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("{roleId}")
    @Operation(description = "Find any role by its id.")
    public ResponseEntity<Role> findById(@PathVariable("roleId") UUID roleId) {
        return roleUseCase.findById(roleId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
