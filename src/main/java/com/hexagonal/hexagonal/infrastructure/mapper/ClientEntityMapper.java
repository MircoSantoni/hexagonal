package com.hexagonal.hexagonal.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.hexagonal.hexagonal.domain.model.Client;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.ClientDto;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateClientCommand;
import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.ClientEntity;

@Mapper(componentModel="spring")
public interface ClientEntityMapper {
    
    Client fromEntityToDomain(ClientEntity clientEntity);  

    ClientEntity fromCommandToEntity(CreateClientCommand createClientCommand);
    
    ClientDto fromDomainToDto(Client client);

    List<ClientDto> fromListDomainToDtoList(List<Client> client);


}
