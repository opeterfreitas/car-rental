package io.github.opeterfreitas.carrental.model.entities;

import io.github.opeterfreitas.carrental.model.entities.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private Category category;
    private Double pricePerDay;
    private Double pricePerHour;

    public Vehicle(String model, Category category, Double pricePerDay, Double pricePerHour) {
        this.model = model;
        this.category = category;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
    }
}