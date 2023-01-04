package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.repositories.CarRentalRepository;
import io.github.opeterfreitas.carrental.model.services.CarRentalService;
import io.github.opeterfreitas.carrental.model.services.exceptions.ObjectNotFoundException;
import io.github.opeterfreitas.carrental.rest.dto.CarRentalDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CarRentalServiceImpl implements CarRentalService {

    private final CarRentalRepository repository;

    @Override
    public CarRental findById(Long id) {
        Optional<CarRental> carRentalOptional = repository.findById(id);
        return carRentalOptional.
                orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id:" + id + ", Tipo: " + CarRental.class.getName()));
    }

    @Override
    public List<CarRental> findAllOpen() {
        List<CarRental> list = repository.findByDateReturnIsNull();
        return list;
    }

    @Override
    public List<CarRental> findAllClose() {
        List<CarRental> list = repository.findByDateReturnNotNull();
        return list;
    }

    @Override
    public List<CarRental> findAll() {
        List<CarRental> list = repository.findAll();
        return list;
    }

    @Override
    @Transactional
    public CarRental create(CarRentalDto dto) {

        Optional<CarRental> existsCarRental = repository
                .existsCarRental(
                        dto.getVehicle(),
                        dto.getStart(),
                        dto.getFinish());

        if (existsCarRental.isEmpty()) {
            CarRental carRental = dto.toModel();
            return repository.save(carRental);
        }
        throw new ObjectNotFoundException(
                "Objeto não encontrado! Tipo: " + CarRental.class.getName());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CarRental carRental = findById(id);
        repository.delete(carRental);
    }

    @Override
    public CarRental update(Long id, CarRentalDto dto) {
        var carRentalExists = findById(id);
        CarRental carRental = dto.toModel();
        carRental.setId(carRentalExists.getId());
        return repository.save(carRental);
    }

//    public void processInvoice(CarRental carRental) {
//
//        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
//        double hours = minutes / 60.0;
//
//        double basicPayment;
//        if (hours <= 12.0) {
//            basicPayment = carRental.getVehicle().getPricePerHour() * Math.ceil(hours);
//        } else {
//            basicPayment = carRental.getVehicle().getPricePerDay() * Math.ceil(hours / 24);
//        }
//
//        double tax = tax(basicPayment);
//
//        carRental.setInvoice(new Invoice(basicPayment, tax));
//    }
//
//    public double tax(double basicPayment) {
//        if (basicPayment <= 100.0) {
//            return basicPayment * 0.2;
//        } else {
//            return basicPayment * 0.15;
//        }
//    }

}