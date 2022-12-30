package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.controller.dto.CarRentalDto;
import io.github.opeterfreitas.carrental.model.entities.CarRental;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CarRentalService {

    CarRental save(CarRentalDto dto);

    List<CarRental> findByDateReturnIsNull();

    List<CarRental> findByDateReturnNotNull();

    List<CarRental> findAll();

    Optional<CarRental> findById(Long id);

    void delete(CarRental carRental);

    CarRental updateCarRental(CarRental carRental);

}