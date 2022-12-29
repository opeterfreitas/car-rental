package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.CarRentalDto;
import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.services.CarRentalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Object> saveCarRental(@RequestBody @Valid CarRentalDto carRentalDto) {
        CarRental carRental = carRentalDto.toModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(carRental));
    }

    @GetMapping(value = "/open")
    public ResponseEntity<List<CarRental>> findByDateReturnIsNull(){
        List<CarRental> list = service.findByDateReturnIsNull();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/close")
    public ResponseEntity<List<CarRental>> findByDateReturnNotNull(){
        List<CarRental> list = service.findByDateReturnNotNull();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<CarRental>> getAllCarRental() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneCarRental(@PathVariable(value = "id") Long id) {
        Optional<CarRental> carRentalOptional = service.findById(id);
        if (!carRentalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarRental not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(carRentalOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarRental(@PathVariable(value = "id") Long id) {
        Optional<CarRental> carRentalOptional = service.findById(id);
        if (!carRentalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarRental not found");
        }
        service.delete(carRentalOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("CarRental deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCarRental(@PathVariable(value = "id") Long id,
                                                  @RequestBody @Valid CarRentalDto carRentalDto) {
        Optional<CarRental> carRentalOptional = service.findById(id);
        if (!carRentalOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("CarRental not found.");
        }
        CarRental carRental = carRentalDto.toModel();
        carRental.setId(carRentalOptional.get().getId());
        carRental.setStart(carRentalOptional.get().getStart());
        carRental.setFinish(carRentalOptional.get().getFinish());
        carRental.setVehicle(carRentalOptional.get().getVehicle());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(carRental));
    }
}