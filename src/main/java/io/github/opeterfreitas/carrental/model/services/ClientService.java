package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.model.entities.Vehicle;
import io.github.opeterfreitas.carrental.rest.dto.ClientDto;
import io.github.opeterfreitas.carrental.model.entities.Client;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ClientService {

    Client findById(Long id);

    List<Client> findAll();

    Client create(ClientDto dto);

    void delete(Long id);

    Client update(Long id, ClientDto dto);

}