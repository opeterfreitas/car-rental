package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.rest.dto.VehicleDto;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface VehicleService {

    Vehicle findById(Long id);

    List<Vehicle> findAll();

    Vehicle create(VehicleDto dto);

    void delete(Long id);

    Vehicle update(Long id, VehicleDto dto);

}