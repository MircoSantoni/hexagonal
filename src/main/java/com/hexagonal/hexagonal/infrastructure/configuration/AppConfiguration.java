package com.hexagonal.hexagonal.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.hexagonal.application.ports.in.ClientServicePort;
import com.hexagonal.hexagonal.application.ports.in.ProductServicePort;
import com.hexagonal.hexagonal.application.ports.out.ClientPersistencePort;
import com.hexagonal.hexagonal.application.ports.out.ProductPersistancePort;
import com.hexagonal.hexagonal.application.ports.usecases.ClientServicePortImpl;
import com.hexagonal.hexagonal.application.ports.usecases.ProductServicePortImpl;

@Configuration
public class AppConfiguration {

    @Bean
    public ClientServicePort clientServicePort(ClientPersistencePort clientPersistencePort) {
        return new ClientServicePortImpl(clientPersistencePort);
    }
    

    @Bean
    ProductServicePort productServicePort(ProductPersistancePort productPersistancePort) {
        return new ProductServicePortImpl(productPersistancePort);
    }
}
