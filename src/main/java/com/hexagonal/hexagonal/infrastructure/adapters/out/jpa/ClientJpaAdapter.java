package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexagonal.hexagonal.application.ports.out.ClientPersistencePort;
import com.hexagonal.hexagonal.domain.model.Client;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateClientCommand;
import com.hexagonal.hexagonal.infrastructure.mapper.ClientEntityMapper;

@Service
public class ClientJpaAdapter implements ClientPersistencePort {

    private final ClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    public ClientJpaAdapter(ClientRepository clientRepository, ClientEntityMapper clientEntityMapper) {
        this.clientRepository = clientRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll().stream()
        .map(clientEntityMapper::fromEntityToDomain)
        .toList();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).map(clientEntityMapper::fromEntityToDomain).orElseThrow(() -> new RuntimeException());

    }

    @Override
    public Client createClient(CreateClientCommand createClientCommand) {
        var entity = clientEntityMapper.fromCommandToEntity(createClientCommand);
        var savedEntity = clientRepository.save(entity);
        return clientEntityMapper.fromEntityToDomain(savedEntity);
    }

    @Override
    public Client updateClient(CreateClientCommand createClientCommand, Long id) {
        var dbEntity = clientRepository.findById(id)
        .orElseThrow(() -> new RuntimeException());

        dbEntity.setFirstName(createClientCommand.firstname());
        dbEntity.setLastName(createClientCommand.lastname());
        dbEntity.setEmail(createClientCommand.email());
        dbEntity.setGender(createClientCommand.gender());
        dbEntity.setIpAddress(createClientCommand.ipAddress());
        
        var updatedEntity = clientRepository.save(dbEntity);
        return clientEntityMapper.fromEntityToDomain(updatedEntity);
    }

    @Override
    public Client deleteClient(Long id) {
        var dbEntity = clientRepository.findById(id).orElseThrow(() -> new RuntimeException());

        clientRepository.deleteById(dbEntity.id);

        return clientEntityMapper.fromEntityToDomain(dbEntity);
    }

}
