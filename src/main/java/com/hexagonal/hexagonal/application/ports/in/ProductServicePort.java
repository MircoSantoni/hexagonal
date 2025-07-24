package com.hexagonal.hexagonal.application.ports.in;

import java.util.List;
import java.math.BigDecimal;

import com.hexagonal.hexagonal.domain.model.Product;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateProductCommand;

public interface ProductServicePort {

    List<Product> listAll();
    Product findbyId(String id);
    Product createProduct(CreateProductCommand command);
    Product changePrice(BigDecimal price, String id);

}
