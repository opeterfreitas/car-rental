package io.github.opeterfreitas.carrental.model.services.impl;

import io.github.opeterfreitas.carrental.model.entities.Client;
import io.github.opeterfreitas.carrental.model.repositories.ClientRepository;
import io.github.opeterfreitas.carrental.model.services.ClientService;
import io.github.opeterfreitas.carrental.model.services.exceptions.ObjectNotFoundException;
import io.github.opeterfreitas.carrental.rest.dto.CepDto;
import io.github.opeterfreitas.carrental.rest.dto.ClientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Override
    public Client findById(Long id) {
        Optional<Client> clientOptional = repository.findById(id);
        return clientOptional.
                orElseThrow(() -> new ObjectNotFoundException(
                        "Objeto não encontrado! Id:" + id + ", Tipo: " + Client.class.getName()));
    }

    @Override
    public List<Client> findAll() {
        List<Client> list = repository.findAll();
        return list;
    }

    @Override
    @Transactional
    public Client create(ClientDto dto) {
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
    @Transactional
    public void delete(Long id) {
        Client client = findById(id);
        repository.delete(client);
    }

    @Override
    @Transactional
    public Client update(Long id, ClientDto dto) {
        var clientExists = findById(id);
        Client client = dto.toModel();
        client.setId(clientExists.getId());
        client.setRegistrationDate(clientExists.getRegistrationDate());
        return repository.save(client);
    }

    public CepDto consultaCep(String cep) {
        return new RestTemplate()
                .getForEntity("https://viacep.com.br/ws/" + cep + "/json/", CepDto.class)
                .getBody();
    }
}