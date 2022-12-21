package io.github.opeterfreitas.carrental.controller;

import io.github.opeterfreitas.carrental.controller.dto.InvoiceDto;
import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/invoices")
@CrossOrigin(origins = "*", maxAge = 3600)
public class InvoiceController {

    @Autowired
    final InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveInvoice(@RequestBody @Valid InvoiceDto invoiceDto) {
        Invoice invoice = invoiceDto.toModel();
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(invoice));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneInvoice(@PathVariable(value = "id") Long id) {
        Optional<Invoice> invoiceOptional = service.findById(id);
        if (!invoiceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(invoiceOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable(value = "id") Long id) {
        Optional<Invoice> invoiceOptional = service.findById(id);
        if (!invoiceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found");
        }
        service.delete(invoiceOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid InvoiceDto invoiceDto) {
        Optional<Invoice> invoiceOptional = service.findById(id);
        if (!invoiceOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invoice not found.");
        }
        Invoice invoice = invoiceDto.toModel();
        invoice.setId(invoiceOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(service.save(invoice));
    }
}