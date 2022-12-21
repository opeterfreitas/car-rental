package io.github.opeterfreitas.carrental.model.services;

import io.github.opeterfreitas.carrental.controller.dto.CepDto;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Client save(Client client) {
        return repository.save(client);
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public void delete(Client client) {
        repository.delete(client);
    }

    public CepDto consultaCep(String cep) {
        return new RestTemplate().getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDto.class).getBody();
    }

}