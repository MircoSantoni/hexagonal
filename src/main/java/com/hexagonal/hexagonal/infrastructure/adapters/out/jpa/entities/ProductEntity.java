package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="products")
@Entity
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;

    private Long sku;

    @Column
    private String productName;

    @Column(name="product_date")
    private LocalDate date;

    private BigDecimal price;


    @PrePersist
    public void onCreate() {
        date= LocalDate.now();
    }

}