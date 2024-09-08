package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.application.dto.CreateClientReqDto;
import com.cesar31.root.application.dto.UpdateClientReqDto;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.exception.EntityNotFoundException;
import com.cesar31.root.application.ports.input.ClientUseCase;
import com.cesar31.root.domain.Client;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientRestAdapter {

    private final ClientUseCase clientUseCase;

    public ClientRestAdapter(ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @GetMapping
    @Operation(description = "Find all clients.")
    public ResponseEntity<List<Client>> findAll() {
        var clients = clientUseCase.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("{clientId}")
    @Operation(description = "Find any client by its id.")
    public ResponseEntity<Client> findById(@PathVariable("clientId") UUID clientId) {
        return clientUseCase.findById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(description = "Create a new client")
    public ResponseEntity<Client> create(@RequestBody CreateClientReqDto reqDto) throws ApplicationException {
        Client newClient = null;
        try {
            newClient = clientUseCase.save(reqDto);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("{clientId}")
    @Operation(description = "Update any client by its id.")
    public ResponseEntity<Client> update(@PathVariable("clientId") UUID clientId, @RequestBody UpdateClientReqDto reqDto) throws Exception {
        var updatedClient = clientUseCase.update(clientId, reqDto);
        return ResponseEntity.ok(updatedClient);
    }
}
