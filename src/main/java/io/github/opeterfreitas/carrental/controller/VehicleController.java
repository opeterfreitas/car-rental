package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.VehicleDto;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/vehicles")
@CrossOrigin(origins = "*")
public class VehicleController {

    private VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveVehicle(@RequestBody @Valid VehicleDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicleOptional = service.findById(id);
        if (vehicleOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(vehicleOptional.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Veículo não encontrado!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable Long id) {
        Optional<Vehicle> vehicleOptional = service.findById(id);
        if (vehicleOptional.isPresent()) {
            service.delete(vehicleOptional.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Veículo deletado com sucesso!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Veículo não encontrado!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@RequestBody @Valid @PathVariable Long id, VehicleDto dto) {
        Optional<Vehicle> vehicleOptional = service.findById(id);
        if (vehicleOptional.isPresent()) {
            Vehicle vehicle = service.save(dto);
            vehicle.setId(vehicleOptional.get().getId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(vehicle);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Veículo não encontrado!");
    }
}