package io.github.opeterfreitas.carrental.model.repositories;

import io.github.opeterfreitas.carrental.model.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
