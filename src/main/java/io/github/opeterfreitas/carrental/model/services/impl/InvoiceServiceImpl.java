package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.repositories.InvoiceRepository;
import io.github.opeterfreitas.carrental.model.services.InvoiceService;
import io.github.opeterfreitas.carrental.model.services.exceptions.ObjectNotFoundException;
import io.github.opeterfreitas.carrental.rest.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository repository;

    @Override
    public Invoice findById(Long id) {
        Optional<Invoice> invoiceOptional = repository.findById(id);
        return invoiceOptional.
                orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id:" + id + ", Tipo: " + Invoice.class.getName()));
    }

    @Override
    public List<Invoice> findAll() {
        List<Invoice> list = repository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Invoice create(InvoiceDto dto) {
        Invoice invoice = dto.toModel();
        return repository.save(invoice);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Invoice invoice = findById(id);
        repository.delete(invoice);
    }

    @Override
    @Transactional
    public Invoice update(Long id, InvoiceDto dto) {
        var invoiceExists = findById(id);
        Invoice invoice = dto.toModel();
        invoice.setId(invoiceExists.getId());
        return repository.save(invoice);
    }
}
