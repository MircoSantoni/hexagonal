package com.hexagonal.hexagonal.application.ports.usecases;

import java.util.List;

import com.hexagonal.hexagonal.application.ports.in.ClientServicePort;
import com.hexagonal.hexagonal.application.ports.out.ClientPersistencePort;
import com.hexagonal.hexagonal.domain.model.Client;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateClientCommand;


public class ClientServicePortImpl implements ClientServicePort {

    private final ClientPersistencePort clientPersistencePort;

    public ClientServicePortImpl(ClientPersistencePort clientPersistencePort) {
        this.clientPersistencePort = clientPersistencePort;
    }

    @Override
    public List<Client> listAll() {
        return clientPersistencePort.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientPersistencePort.findById(id);
    }

    @Override
    public Client createClient(CreateClientCommand createClientCommand) {
        return clientPersistencePort.createClient(createClientCommand);
    }

    @Override
    public Client updateClient(CreateClientCommand createClientCommand, Long id) {
        return clientPersistencePort.updateClient(createClientCommand, id);
    }

    @Override
    public Client deleteClient(Long id) {
        return clientPersistencePort.deleteClient(id);
    }



    
}
