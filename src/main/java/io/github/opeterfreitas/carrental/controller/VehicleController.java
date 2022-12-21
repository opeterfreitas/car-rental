package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.VehicleDto;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.services.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/vehicles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VehicleController {

    @Autowired
    final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveVehicle(@RequestBody @Valid VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleDto.toModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(vehicle));
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVehicle(@PathVariable(value = "id") Long id) {
        Optional<Vehicle> vehicleOptional = service.findById(id);
        if (!vehicleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(vehicleOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVehicle(@PathVariable(value = "id") Long id) {
        Optional<Vehicle> vehicleOptional = service.findById(id);
        if (!vehicleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found");
        }
        service.delete(vehicleOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vehicle deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVehicle(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid VehicleDto vehicleDto) {
        Optional<Vehicle> vehicleOptional = service.findById(id);
        if (!vehicleOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vehicle not found.");
        }
        Vehicle vehicle = vehicleDto.toModel();
        vehicle.setId(vehicleOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(vehicle));
    }
}