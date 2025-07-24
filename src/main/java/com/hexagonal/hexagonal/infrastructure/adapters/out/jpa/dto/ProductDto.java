package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductDto(
    
    String id,
    Long sku,
    String productName,
    LocalDate date,
    BigDecimal price

) { }
