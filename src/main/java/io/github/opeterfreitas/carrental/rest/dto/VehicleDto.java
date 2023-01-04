package io.github.opeterfreitas.carrental.rest.dto;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.entities.enums.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String model;
    private Category category;
    private Double pricePerDay;
    private Double pricePerHour;

    public Vehicle toModel() {
        return new Vehicle(this.model, this.category, this.pricePerDay, this.pricePerHour);
    }
}