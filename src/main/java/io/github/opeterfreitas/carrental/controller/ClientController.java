package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.ClientDto;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ClientDto dto) {
        Client client = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") Long id) {
        Optional<Client> clientOptional = service.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(clientOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") Long id) {
        Optional<Client> clientOptional = service.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found");
        }
        service.delete(clientOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid ClientDto dto) {
        Optional<Client> clientOptional = service.findById(id);
        if (!clientOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client not found.");
        }

        Client client = dto.toModel();
        client.setId(clientOptional.get().getId());
        client.setRegistrationDate(clientOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(service.updateClient(client));
    }
}
