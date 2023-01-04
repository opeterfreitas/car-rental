package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.rest.dto.InvoiceDto;
import io.github.opeterfreitas.carrental.model.entities.Invoice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InvoiceService {

    Invoice findById(Long id);

    List<Invoice> findAll();

    Invoice create(InvoiceDto dto);

    void delete(Long id);

    Invoice update(Long id, InvoiceDto dto);

}