package io.github.opeterfreitas.carrental.rest.controller;

import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.services.ClientService;
import io.github.opeterfreitas.carrental.rest.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client client = service.findById(id);
        return ResponseEntity.ok().body(client);
    }

    @GetMapping
    public ResponseEntity<List<Client>> findAll() {
        List<Client> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDto dto) {
        Client client = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody ClientDto dto) {
        Client client = service.update(id, dto);
        return ResponseEntity.ok().body(client);
    }
}