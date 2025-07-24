package com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto;

import com.hexagonal.hexagonal.domain.model.ennums.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateClientCommand(
    
    @NotBlank(message = "El nombre es requerido para poder crear un cliente")
    @Pattern(regexp = "")
    String firstName,

    @NotBlank(message = "El apellido es requerido para poder crear un cliente")
    String lastName,

    @NotBlank(message = "El nombre es requerido para poder crear un cliente")
    String country,

    @NotBlank(message = "El correo que debe cargar debe ser valido para crear un cliente")
    @Email(message = "El correo que debe cargar debe ser valido para crear un cliente")
    String email,

    @NotBlank(message = "El genero es necesario para crear un cliente")
    Gender gender,

    @NotBlank(message = "La direccion IP es requerida para poder crear un cliente")
    String ipAddress

) { }
