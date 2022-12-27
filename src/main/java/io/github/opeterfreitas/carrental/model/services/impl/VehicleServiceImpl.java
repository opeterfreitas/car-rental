package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.controller.dto.VehicleDto;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.repositories.VehicleRepository;
import io.github.opeterfreitas.carrental.model.services.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private VehicleRepository repository;

    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Vehicle save(VehicleDto dto) {
        Vehicle vehicle = dto.toModel();
        return repository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Vehicle vehicle) {
        repository.delete(vehicle);
    }

    @Override
    public List<Vehicle> findAll() {
        return repository.findAll();
    }
}