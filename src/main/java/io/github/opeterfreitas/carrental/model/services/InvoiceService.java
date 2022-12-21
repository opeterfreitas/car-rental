package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.repositories.InvoiceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    final InvoiceRepository repository;

    public InvoiceService(InvoiceRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Invoice save(Invoice invoice) {
        return repository.save(invoice);
    }

    public List<Invoice> findAll() {
        return repository.findAll();
    }

    public Optional<Invoice> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Invoice invoice) {
        repository.delete(invoice);
    }
}