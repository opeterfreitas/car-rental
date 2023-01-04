package io.github.opeterfreitas.carrental.rest.controller;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.services.CarRentalService;
import io.github.opeterfreitas.carrental.rest.dto.CarRentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/rentals")
@CrossOrigin(origins = "*")
public class CarRentalController {

    private final CarRentalService service;

    @GetMapping("/{id}")
    public ResponseEntity<CarRental> findById(@PathVariable Long id) {
        CarRental carRental = service.findById(id);
        return ResponseEntity.ok().body(carRental);
    }

    @GetMapping("/open")
    public ResponseEntity<List<CarRental>> findAllOpen() {
        List<CarRental> list = service.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/close")
    public ResponseEntity<List<CarRental>> findAllClose() {
        List<CarRental> list = service.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<CarRental>> findAll() {
        List<CarRental> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<CarRental> create(@RequestBody CarRentalDto dto) {
        CarRental carRental = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carRental.getId()).toUri();
        return ResponseEntity.created(uri).body(carRental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarRental> update(@PathVariable Long id, @RequestBody CarRentalDto dto) {
        CarRental carRental = service.update(id, dto);
        return ResponseEntity.ok().body(carRental);
    }
}