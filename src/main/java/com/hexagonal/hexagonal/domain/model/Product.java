package com.hexagonal.hexagonal.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public record Product(
    
    String id,
    Long sku,
    String productName,
    LocalDate date,
    BigDecimal price

) { }
