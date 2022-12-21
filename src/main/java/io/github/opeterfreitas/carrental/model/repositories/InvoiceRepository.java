package io.github.opeterfreitas.carrental.model.repositories;

import io.github.opeterfreitas.carrental.model.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
