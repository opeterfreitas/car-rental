package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.entities.enums.Category;
import io.github.opeterfreitas.carrental.model.repositories.CarRentalRepository;
import io.github.opeterfreitas.carrental.model.repositories.ClientRepository;
import io.github.opeterfreitas.carrental.model.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private CarRentalRepository carRentalRepository;

    public void instanciaBaseDeDados() {

        Client client1 = new Client("38681086022", "Cliente 1 da Silva", "client1@email.com", "38414001", "Rua do Cliente 1", "Complemento do Cliente 1", "541", "Dona Zulmira 1", "Uberlândia", "MG");
        Client client2 = new Client("45747270044", "Cliente 2 da Silva", "client2@email.com", "38414002", "Rua do Cliente 2", "Complemento do Cliente 2", "542", "Dona Zulmira 2", "Uberlândia", "MG");
        Client client3 = new Client("41580844073", "Cliente 3 da Silva", "client3@email.com", "38414003", "Rua do Cliente 3", "Complemento do Cliente 3", "543", "Dona Zulmira 3", "Uberlândia", "MG");
        Client client4 = new Client("26158531049", "Cliente 4 da Silva", "client4@email.com", "38414004", "Rua do Cliente 4", "Complemento do Cliente 4", "544", "Dona Zulmira 4", "Uberlândia", "MG");
        Client client5 = new Client("93889717012", "Cliente 5 da Silva", "client5@email.com", "38414005", "Rua do Cliente 5", "Complemento do Cliente 5", "545", "Dona Zulmira 5", "Uberlândia", "MG");

        Vehicle vehicle1 = new Vehicle("Gol G5", Category.HATCH, 130.0, 10.0);
        Vehicle vehicle2 = new Vehicle("Gol G6", Category.HATCH, 140.0, 13.0);
        Vehicle vehicle3 = new Vehicle("Onix", Category.HATCH, 150.0, 18.0);
        Vehicle vehicle4 = new Vehicle("Uno Fire", Category.HATCH, 160.0, 20.0);
        Vehicle vehicle5 = new Vehicle("Sandero", Category.HATCH, 170.0, 30.0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        CarRental carRental1 = new CarRental(LocalDateTime.parse("10/12/2022 10:40", formatter), LocalDateTime.parse("11/12/2022 08:00", formatter), client1, vehicle1, LocalDateTime.parse("13/12/2022 08:00", formatter));
        CarRental carRental2 = new CarRental(LocalDateTime.parse("11/12/2022 13:40", formatter), LocalDateTime.parse("15/12/2022 09:00", formatter), client2, vehicle2, LocalDateTime.parse("16/12/2022 08:00", formatter));
        CarRental carRental3 = new CarRental(LocalDateTime.parse("15/12/2022 10:40", formatter), LocalDateTime.parse("25/12/2022 08:00", formatter), client3, vehicle3, null);
        CarRental carRental4 = new CarRental(LocalDateTime.parse("16/12/2022 10:40", formatter), LocalDateTime.parse("16/12/2022 08:00", formatter), client4, vehicle4, LocalDateTime.parse("16/12/2022 08:00", formatter));
        CarRental carRental5 = new CarRental(LocalDateTime.parse("21/12/2022 10:40", formatter), LocalDateTime.parse("25/12/2022 08:00", formatter), client5, vehicle5, null);

        clientRepository.saveAll(Arrays.asList(client1, client2, client3, client4, client5));
        vehicleRepository.saveAll(Arrays.asList(vehicle1, vehicle2, vehicle3, vehicle4, vehicle5));
        carRentalRepository.saveAll(Arrays.asList(carRental1, carRental2, carRental3, carRental4, carRental5));

    }
}
