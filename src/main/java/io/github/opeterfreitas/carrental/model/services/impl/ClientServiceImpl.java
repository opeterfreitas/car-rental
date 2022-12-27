package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.controller.dto.CepDto;
import io.github.opeterfreitas.carrental.controller.dto.ClientDto;
import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.repositories.ClientRepository;
import io.github.opeterfreitas.carrental.model.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Client save(ClientDto dto) {

        CepDto cepDto = consultaCep(dto.getCep());

        dto.setLogradouro(cepDto.getLogradouro());
        dto.setBairro(cepDto.getBairro());
        dto.setLocalidade(cepDto.getLocalidade());
        dto.setUf(cepDto.getUf());

        Client client = dto.toModel();

        client.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));

        return repository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Client client) {
        repository.delete(client);
    }

    @Override
    public List<Client> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public Client updateClient(Client client) {
        return repository.save(client);
    }

    public CepDto consultaCep(String cep) {
        return new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDto.class)
                .getBody();
    }
}