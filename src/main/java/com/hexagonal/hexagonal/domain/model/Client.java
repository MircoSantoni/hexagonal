package com.hexagonal.hexagonal.domain.model;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;

public record Client(
    Long id,
    String firstname,
    String email,
    Gender gender,
    String ipAddress
) { }