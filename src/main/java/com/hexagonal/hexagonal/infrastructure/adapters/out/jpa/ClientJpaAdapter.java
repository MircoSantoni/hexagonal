package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexagonal.hexagonal.application.ports.out.ClientPersistencePort;
import com.hexagonal.hexagonal.domain.model.Client;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateClientCommand;
import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.entities.ClientEntity;
import com.hexagonal.hexagonal.infrastructure.exceptions.EmailAlreadyInUseException;
import com.hexagonal.hexagonal.infrastructure.exceptions.EmptyResourceException;
import com.hexagonal.hexagonal.infrastructure.exceptions.ListDoesNotExistException;
import com.hexagonal.hexagonal.infrastructure.mapper.ClientMapper;

@Service
public class ClientJpaAdapter implements ClientPersistencePort {

    private final ClientRepository clientRepository;
    private final ClientMapper clientEntityMapper;

    public ClientJpaAdapter(ClientRepository clientRepository, ClientMapper clientEntityMapper) {
        this.clientRepository = clientRepository;
        this.clientEntityMapper = clientEntityMapper;
    }

    @Override
    public List<Client> findAll() {

        try {
            return clientRepository.findAll().stream()
                    .map(clientEntityMapper::fromEntityToDomain)
                    .toList();
        } catch (Exception exception) {
            throw new ListDoesNotExistException("Error al cargar la lista de clientes");
        }
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).map(clientEntityMapper::fromEntityToDomain)
                .orElseThrow(() -> new EmptyResourceException("No existe ningun cliente con ese id"));

    }

    @Override
    public Client createClient(CreateClientCommand createClientCommand) {
        var client = clientRepository.findByEmail(createClientCommand.email());
        if (client.isPresent()) {
            throw new EmailAlreadyInUseException("El email ya esta en uso por parte de otro usuario");
        }
        try {

            var entity = clientEntityMapper.fromCommandToEntity(createClientCommand);
            var savedEntity = clientRepository.save(entity);
            return clientEntityMapper.fromEntityToDomain(savedEntity);

        } catch (RuntimeException exception) {
            throw new RuntimeException("Excepcion general al crear el cliente");
        }
    }

    @Override
    public Client updateClient(CreateClientCommand createClientCommand, Long id) {
        var dbEntity = clientRepository.findById(id)
                .orElseThrow(() -> new EmptyResourceException("No existe ningun cliente con ese id"));

        var client = clientRepository.findByEmail(createClientCommand.email());
        if (client.isPresent()) {
            throw new EmailAlreadyInUseException("El email ya esta en uso por parte de otro usuario");
        }
        try {

            dbEntity.setFirstName(createClientCommand.firstName());
            dbEntity.setLastName(createClientCommand.lastName());
            dbEntity.setEmail(createClientCommand.email());
            dbEntity.setCountry(createClientCommand.country());
            dbEntity.setGender(createClientCommand.gender());
            dbEntity.setIpAddress(createClientCommand.ipAddress());

            var updatedEntity = clientRepository.save(dbEntity);
            return clientEntityMapper.fromEntityToDomain(updatedEntity);
        } catch (Exception exception) {
            throw new RuntimeException("Error general al modificar el cliente");
        }
    }

    @Override
    public Client deleteClient(Long id) {

        ClientEntity dbEntity = clientRepository.findById(id)
                .orElseThrow(() -> new EmptyResourceException("No existe ningun cliente con ese id"));

        try {

            clientRepository.deleteById(dbEntity.id);

            return clientEntityMapper.fromEntityToDomain(dbEntity);

        } catch (Exception exception) {
            throw new RuntimeException("Error general al borrar el cliente");
        }
    }

}
