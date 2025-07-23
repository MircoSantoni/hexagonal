package com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;

public record ClientDto(
    
    Long id,
    String firstname,
    String email,
    Gender gender,
    String ipAddress
    
) { }
