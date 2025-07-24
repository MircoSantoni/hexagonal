package com.hexagonal.hexagonal.infrastructure.adapters.in.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.hexagonal.application.ports.in.ClientServicePort;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateClientCommand;
import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.dto.ClientDto;
import com.hexagonal.hexagonal.infrastructure.mapper.ClientMapper;


@RestController
@RequestMapping("/api/customer/")
public class ClientController {


    private final ClientServicePort clientServicePort;
    private final ClientMapper clientEntityMapper;

    public ClientController(ClientServicePort clientServicePort, ClientMapper clientEntityMapper) {
        this.clientServicePort = clientServicePort;
        this.clientEntityMapper = clientEntityMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDto>> listAll() {
        return ResponseEntity.ok(clientEntityMapper.fromListDomainToDtoList(clientServicePort.listAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ClientDto> findbyId(@PathVariable Long id) {
        return ResponseEntity.ok(clientEntityMapper.fromDomainToDto(clientServicePort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ClientDto> createClient(@RequestBody CreateClientCommand createClientCommand) {
        return ResponseEntity.ok(clientEntityMapper.fromDomainToDto(clientServicePort.createClient(createClientCommand)));
    }

    @PutMapping("{id}")
    public ResponseEntity<ClientDto> updateCliEntity(@PathVariable Long id, @RequestBody CreateClientCommand createClientCommand) {        
        return ResponseEntity.ok(clientEntityMapper.fromDomainToDto(clientServicePort.updateClient(createClientCommand, id)));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientEntityMapper.fromDomainToDto(clientServicePort.deleteClient(id)));
    }

}
