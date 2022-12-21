package io.github.opeterfreitas.carrental.controller.dto;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CarRentalDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime start;
    private LocalDateTime finish;
    private Client client;
    private Vehicle vehicle;
    private LocalDateTime dateReturn;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public void setFinish(LocalDateTime finish) {
        this.finish = finish;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDateTime dateReturn) {
        this.dateReturn = dateReturn;
    }

    public CarRental toModel() {
        return new CarRental(this.start, this.finish, this.client, this.vehicle, this.dateReturn);
    }
}