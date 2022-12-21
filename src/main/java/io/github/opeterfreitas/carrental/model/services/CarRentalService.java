package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.repositories.CarRentalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class CarRentalService {

    @Autowired
    final CarRentalRepository repository;

    public CarRentalService(CarRentalRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public CarRental save(CarRental carRental) {
        boolean existsByVehiclesAndStartAndFinish = repository
                .existsByVehicleAndStartAndFinish(carRental.getVehicle(), carRental.getStart(), carRental.getFinish());
        boolean existsByDateReturn = repository
                .existsByDateReturn(carRental.getDateReturn());
        if ((existsByVehiclesAndStartAndFinish) && (!existsByDateReturn)) {
            processInvoice(carRental);
            return repository.save(carRental);
        }
        return repository.save(carRental);
    }

    public Optional<CarRental> findById(Long id) {
        return repository.findById(id);
    }

    public List<CarRental> findAll() {
        return repository.findAll();
    }

    @Transactional
    public void delete(CarRental carRental) {
        repository.delete(carRental);
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