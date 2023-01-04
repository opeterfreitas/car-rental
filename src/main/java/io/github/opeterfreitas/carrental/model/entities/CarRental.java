package io.github.opeterfreitas.carrental.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CarRental implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime finish;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Vehicle vehicle;
    @OneToOne(cascade = CascadeType.ALL)
    private Invoice invoice;
    private LocalDateTime dateReturn;

    public CarRental(LocalDateTime start, LocalDateTime finish, Client client, Vehicle vehicle, LocalDateTime dateReturn) {
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.vehicle = vehicle;
        this.dateReturn = dateReturn;
    }
}