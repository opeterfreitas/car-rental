package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.ClientDto;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientController {

    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ClientDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable Long id) {
        Optional<Client> clientOptional = service.findById(id);
        if (clientOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(clientOptional.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Client not found");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable Long id) {
        Optional<Client> clientOptional = service.findById(id);
        if (clientOptional.isPresent()) {
            service.delete(clientOptional.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Cliente deletado com sucesso!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Cliente não encontrado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@RequestBody @Valid @PathVariable Long id, ClientDto dto) {
        Optional<Client> clientOptional = service.findById(id);
        if (clientOptional.isPresent()) {
            Client client = dto.toModel();
            client.setId(clientOptional.get().getId());
            client.setRegistrationDate(clientOptional.get().getRegistrationDate());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.updateClient(client));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Client not found.");
    }
}