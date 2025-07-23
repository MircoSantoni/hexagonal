package com.hexagonal.hexagonal.infrastructure.configuration;

import org.springframework.context.annotation.Bean;

import com.hexagonal.hexagonal.application.ports.in.ClientServicePort;
import com.hexagonal.hexagonal.application.ports.out.ClientPersistencePort;
import com.hexagonal.hexagonal.application.ports.usecases.ClientServicePortImpl;


public class AppConfiguration {

    @Bean
    public ClientServicePort clientServicePort(ClientPersistencePort clientPersistencePort) {
        return new ClientServicePortImpl(clientPersistencePort);
    }
    //a
}
