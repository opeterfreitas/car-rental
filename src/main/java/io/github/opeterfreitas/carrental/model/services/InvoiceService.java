package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.controller.dto.InvoiceDto;
import io.github.opeterfreitas.carrental.controller.dto.VehicleDto;
import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.model.repositories.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface InvoiceService {

    Invoice save(InvoiceDto dto);

    Optional<Invoice> findById(Long id);

    void delete(Invoice invoice);

    List<Invoice> findAll();
}