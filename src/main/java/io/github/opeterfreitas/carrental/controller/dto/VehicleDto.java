package io.github.opeterfreitas.carrental.controller.dto;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.entities.enums.Category;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class VehicleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String model;
    private Category category;
    private Double pricePerDay;
    private Double pricePerHour;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Vehicle toModel() {
        return new Vehicle(this.model, this.category, this.pricePerDay, this.pricePerHour);
    }
}