package io.github.opeterfreitas.carrental.model.repositories;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long> {

    boolean existsByVehicleAndStartAndFinish(Vehicle vehicle, LocalDateTime start, LocalDateTime finish);
    boolean existsByDateReturn(LocalDateTime dateReturn);
    List<CarRental> findByDateReturnIsNull();
    List<CarRental> findByDateReturnNotNull();
}