package com.hexagonal.hexagonal.application.ports.out;

import java.math.BigDecimal;
import java.util.List;

import com.hexagonal.hexagonal.domain.model.Product;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateProductCommand;

public interface ProductPersistancePort {

    List<Product> listAll();
    Product findbyId(String id);
    Product createProduct(CreateProductCommand command);
    Product changePrice(BigDecimal price, String id);
    
}
