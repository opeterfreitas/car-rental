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
@RequestMapping(value = "/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {

    private InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> saveInvoice(@RequestBody @Valid InvoiceDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.save(dto));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneInvoice(@PathVariable Long id) {
        Optional<Invoice> invoiceOptional = service.findById(id);
        if (invoiceOptional.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(invoiceOptional.get());
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Fatura não encontrada!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
        Optional<Invoice> invoiceOptional = service.findById(id);
        if (invoiceOptional.isPresent()) {
            service.delete(invoiceOptional.get());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Fatura deletada com sucesso!");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Fatura não encontrada!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@RequestBody @Valid @PathVariable Long id, InvoiceDto dto) {
        Optional<Invoice> invoiceOptional = service.findById(id);
        if (invoiceOptional.isPresent()) {
            Invoice invoice = dto.toModel();
            invoice.setId(invoiceOptional.get().getId());
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.save(dto));
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Fatura não encontrada!");
    }
}