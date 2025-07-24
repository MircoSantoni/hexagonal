package com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;

public record CreateProductCommand(

    @NotBlank(message="El codigo es requerido para crear un producto")
    Long sku,

    @NotBlank(message="El nombre es requerido para crear un producto")
    String productName,

    @NotBlank(message="El precio es requerido para crear un producto")
    BigDecimal price

) { }
