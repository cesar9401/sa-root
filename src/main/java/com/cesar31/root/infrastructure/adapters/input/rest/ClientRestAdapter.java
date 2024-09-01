package com.cesar31.root.infrastructure.adapters.input.rest;

import com.cesar31.root.application.dto.CreateClientReqDto;
import com.cesar31.root.application.exception.ApplicationException;
import com.cesar31.root.application.ports.input.ClientUseCase;
import com.cesar31.root.domain.Client;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientRestAdapter {

    private final ClientUseCase clientUseCase;

    public ClientRestAdapter(ClientUseCase clientUseCase) {
        this.clientUseCase = clientUseCase;
    }

    @PostMapping
    @Operation(description = "Create a new client")
    public ResponseEntity<Client> create(@RequestBody CreateClientReqDto reqDto) throws ApplicationException {
        var newClient = clientUseCase.save(reqDto);
        return ResponseEntity.ok(newClient);
    }
}
