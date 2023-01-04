package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.repositories.VehicleRepository;
import io.github.opeterfreitas.carrental.model.services.VehicleService;
import io.github.opeterfreitas.carrental.model.services.exceptions.ObjectNotFoundException;
import io.github.opeterfreitas.carrental.rest.dto.VehicleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;

    @Override
    public Vehicle findById(Long id) {
        Optional<Vehicle> vehicleOptional = repository.findById(id);
        return vehicleOptional.
                orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id:" + id + ", Tipo: " + Vehicle.class.getName()));
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> list = repository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Vehicle create(VehicleDto dto) {
        Vehicle vehicle = dto.toModel();
        return repository.save(vehicle);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Vehicle vehicle = findById(id);
        repository.delete(vehicle);
    }

    @Override
    @Transactional
    public Vehicle update(Long id, VehicleDto dto) {
        var vehicleExists = findById(id);
        Vehicle vehicle = dto.toModel();
        vehicle.setId(vehicleExists.getId());
        return repository.save(vehicle);
    }
}