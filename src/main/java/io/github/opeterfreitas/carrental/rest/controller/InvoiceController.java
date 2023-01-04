package io.github.opeterfreitas.carrental.rest.controller;

import io.github.opeterfreitas.carrental.model.entities.Invoice;
import io.github.opeterfreitas.carrental.model.services.InvoiceService;
import io.github.opeterfreitas.carrental.rest.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> findById(@PathVariable Long id) {
        Invoice invoice = service.findById(id);
        return ResponseEntity.ok().body(invoice);
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> findAll() {
        List<Invoice> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Invoice> create(@RequestBody InvoiceDto dto) {
        Invoice invoice = service.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(invoice.getId()).toUri();
        return ResponseEntity.created(uri).body(invoice);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> update(@PathVariable Long id, @RequestBody InvoiceDto dto ){
        Invoice invoice = service.update(id, dto);
        return ResponseEntity.ok().body(invoice);
    }
}