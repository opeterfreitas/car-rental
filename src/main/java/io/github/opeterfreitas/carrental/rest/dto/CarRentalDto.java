package io.github.opeterfreitas.carrental.rest.dto;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarRentalDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private LocalDateTime start;
    private LocalDateTime finish;
    private Client client;
    private Vehicle vehicle;
    private LocalDateTime dateReturn;

    public CarRental toModel() {
        return new CarRental(this.start, this.finish, this.client, this.vehicle, this.dateReturn);
    }
}