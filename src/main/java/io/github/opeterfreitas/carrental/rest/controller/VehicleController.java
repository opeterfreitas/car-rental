package io.github.opeterfreitas.carrental.rest.controller;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.services.VehicleService;
import io.github.opeterfreitas.carrental.rest.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private final VehicleService service;

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> findById(@PathVariable Long id) {
        Vehicle vehicle = service.findById(id);
        return ResponseEntity.ok().body(vehicle);
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> findAll() {
        List<Vehicle> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Vehicle> create(@RequestBody VehicleDto dto) {
        Vehicle vehicle = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vehicle.getId()).toUri();
        return ResponseEntity.created(uri).body(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> update(@PathVariable Long id, @RequestBody VehicleDto dto) {
        Vehicle vehicle = service.update(id, dto);
        return ResponseEntity.ok().body(vehicle);
    }
}