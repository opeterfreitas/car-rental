package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.controller.dto.VehicleDto;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VehicleService {

    Vehicle save(VehicleDto dto);

    Optional<Vehicle> findById(Long id);

    void delete(Vehicle vehicle);

    List<Vehicle> findAll();

}