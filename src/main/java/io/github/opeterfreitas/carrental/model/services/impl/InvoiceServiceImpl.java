package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.controller.dto.InvoiceDto;
import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.repositories.InvoiceRepository;
import io.github.opeterfreitas.carrental.model.services.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository repository;

    public InvoiceServiceImpl(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Invoice save(InvoiceDto dto) {
        Invoice invoice = dto.toModel();
        return repository.save(invoice);
    }

    @Override
    public List<Invoice> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Invoice invoice) {
        repository.delete(invoice);
    }
}
