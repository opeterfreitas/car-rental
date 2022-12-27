package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.controller.dto.ClientDto;
import io.github.opeterfreitas.carrental.model.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {

    Client save(ClientDto dto);

    Optional<Client> findById(Long id);

    void delete(Client client);

    List<Client> findAll();

    Client updateClient(Client client);
}