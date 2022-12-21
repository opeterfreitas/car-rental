package io.github.opeterfreitas.carrental.controller.dto;

import io.github.opeterfreitas.carrental.model.entities.CarRental;
import io.github.opeterfreitas.carrental.model.entities.Invoice;

import java.io.Serializable;

public class InvoiceDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double basicPayment;
    private Double tax;

    public Double getBasicPayment() {
        return basicPayment;
    }

    public void setBasicPayment(Double basicPayment) {
        this.basicPayment = basicPayment;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Invoice toModel() {
        return new Invoice(this.basicPayment, this.tax);
    }

}
