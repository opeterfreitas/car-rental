package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.rest.dto.CarRentalDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CarRentalService {

    CarRental findById(Long id);

    List<CarRental> findAllOpen();

    List<CarRental> findAllClose();

    List<CarRental> findAll();

    CarRental create(CarRentalDto dto);

    void delete(Long id);

    CarRental update(Long id, CarRentalDto dto);

}