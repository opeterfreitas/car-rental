package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.controller.dto.CarRentalDto;
import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.repositories.CarRentalRepository;
import io.github.opeterfreitas.carrental.model.services.CarRentalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentalServiceImpl implements CarRentalService {

    private CarRentalRepository repository;
    public CarRentalServiceImpl(CarRentalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public CarRental save(CarRentalDto dto) {
        boolean existsByVehiclesAndStartAndFinish = repository
                .existsByVehicleAndStartAndFinish(
                        dto.getVehicle(),
                        dto.getStart(),
                        dto.getFinish());

        boolean existsByDateReturn = repository
                .existsByDateReturn(
                        dto.getDateReturn());

        if (!existsByVehiclesAndStartAndFinish) {
            CarRental carRental = dto.toModel();
            return repository.save(carRental);
        }
        return null;
    }

    @Override
    public Optional<CarRental> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CarRental> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void delete(CarRental carRental) {
        repository.delete(carRental);
    }

    @Override
    @Transactional
    public CarRental updateCarRental(CarRental carRental) {
        processInvoice(carRental);
        return repository.save(carRental);
    }

    public void processInvoice(CarRental carRental) {

        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60.0;

        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = carRental.getVehicle().getPricePerHour() * Math.ceil(hours);
        } else {
            basicPayment = carRental.getVehicle().getPricePerDay() * Math.ceil(hours / 24);
        }

        double tax = tax(basicPayment);

        carRental.setInvoice(new Invoice(basicPayment, tax));
    }

    public double tax(double basicPayment) {
        if (basicPayment <= 100.0) {
            return basicPayment * 0.2;
        } else {
            return basicPayment * 0.15;
        }
    }

    public List<CarRental> findByDateReturnIsNull() {
        List<CarRental> list = repository.findByDateReturnIsNull();
        return list;
    }

    public List<CarRental> findByDateReturnNotNull() {
        List<CarRental> list = repository.findByDateReturnNotNull();
        return list;
    }
}