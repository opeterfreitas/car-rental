package io.github.opeterfreitas.carrental.model.repositories;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}
