package io.github.opeterfreitas.carrental.model.repositories;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRentalRepository extends JpaRepository<CarRental, Long> {

    @Query("SELECT obj.start FROM CarRental obj WHERE obj.vehicle = :vehicle AND (obj.start BETWEEN :start AND :finish)")
    Optional<CarRental> existsCarRental(@Param("vehicle") Vehicle vehicle, @Param("start") LocalDateTime start, @Param("finish") LocalDateTime finish);

    List<CarRental> findByDateReturnIsNull();

    List<CarRental> findByDateReturnNotNull();
}