package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.CarRentalDto;
import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.services.CarRentalService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/rentals")
@CrossOrigin(origins = "*")
public class CarRentalController {

    private CarRentalService service;

    public CarRentalController(CarRentalService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveCarRental(@RequestBody @Valid CarRentalDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(dto));
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<CarRental>> findByDateReturnIsNull() {
        List<CarRental> list = service.findByDateReturnIsNull();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<List<CarRental>> findByDateReturnNotNull() {
        List<CarRental> list = service.findByDateReturnNotNull();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(list);
    }

    @GetMapping
    public ResponseEntity<List<CarRental>> getAllCarRental() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCarRental(@PathVariable Long id) {
        Optional<CarRental> carRentalOptional = service.findById(id);
        if (carRentalOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(carRentalOptional.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Locação não encontrada!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarRental(@PathVariable Long id) {
        Optional<CarRental> carRentalOptional = service.findById(id);
        if (carRentalOptional.isPresent()) {
            service.delete(carRentalOptional.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Locação deletada com sucesso!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Locação não encontrada!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarRental(@RequestBody @Valid @PathVariable Long id, CarRentalDto dto) {
        Optional<CarRental> carRentalOptional = service.findById(id);
        if (carRentalOptional.isPresent()) {
            CarRental carRental = dto.toModel();
            carRental.setId(carRentalOptional.get().getId());
            carRental.setStart(carRentalOptional.get().getStart());
            carRental.setFinish(carRentalOptional.get().getFinish());
            carRental.setVehicle(carRentalOptional.get().getVehicle());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.updateCarRental(carRental));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Locação não encontrada!");
    }
}