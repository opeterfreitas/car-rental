package io.github.opeterfreitas.carrental.model.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    public CarRental() {
    }

    public CarRental(LocalDateTime start, LocalDateTime finish, Client client, Vehicle vehicle, LocalDateTime dateReturn) {
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.vehicle = vehicle;
        this.dateReturn = dateReturn;
    }

    public CarRental(Long id, LocalDateTime start, LocalDateTime finish, Client client, Vehicle vehicle, Invoice invoice, LocalDateTime dateReturn) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.client = client;
        this.vehicle = vehicle;
        this.invoice = invoice;
        this.dateReturn = dateReturn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public LocalDateTime getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDateTime dateReturn) {
        this.dateReturn = dateReturn;
    }
}