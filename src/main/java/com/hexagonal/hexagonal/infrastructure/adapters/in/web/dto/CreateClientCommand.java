package com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;

public record CreateClientCommand(
    
    Long id,
    String firstname,
    String lastname,
    String email,
    Gender gender,
    String ipAddress

) { }
