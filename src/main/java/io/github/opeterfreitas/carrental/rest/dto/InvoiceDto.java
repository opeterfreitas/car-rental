package io.github.opeterfreitas.carrental.rest.dto;

import io.github.opeterfreitas.carrental.model.entities.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double basicPayment;
    private Double tax;

    public Invoice toModel() {
        return new Invoice(this.basicPayment, this.tax);
    }
}