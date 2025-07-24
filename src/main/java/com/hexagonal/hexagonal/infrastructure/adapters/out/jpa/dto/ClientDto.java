package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.dto;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;

public record ClientDto(
    
    Long id,
    String firstName,
    String lastName,
    String country,
    String email,
    Gender gender,
    String ipAddress
    
) { }
