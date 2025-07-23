package com.hexagonal.hexagonal.application.ports.out;

import java.util.List;

import com.hexagonal.hexagonal.domain.model.Client;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateClientCommand;

public interface ClientPersistencePort {

    List<Client> findAll();
    Client findById(Long id);
    Client createClient(CreateClientCommand createClientCommand);
    Client updateClient(CreateClientCommand createClientCommand, Long id);
    Client deleteClient(Long id);

}
