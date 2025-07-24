package com.hexagonal.hexagonal.application.ports.usecases;

import java.math.BigDecimal;
import java.util.List;

import com.hexagonal.hexagonal.application.ports.in.ProductServicePort;
import com.hexagonal.hexagonal.application.ports.out.ProductPersistancePort;
import com.hexagonal.hexagonal.domain.model.Product;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateProductCommand;

public class ProductServicePortImpl implements ProductServicePort{

    private final ProductPersistancePort productPersistancePort;
    
    public ProductServicePortImpl(ProductPersistancePort productPersistancePort) {
        this.productPersistancePort = productPersistancePort;
    }

    @Override
    public List<Product> listAll() {
        return productPersistancePort.listAll();
    }

    @Override
    public Product findbyId(String id) {
        return productPersistancePort.findbyId(id);
    }

    @Override
    public Product createProduct(CreateProductCommand command) {
        return productPersistancePort.createProduct(command);
    }

    @Override
    public Product changePrice(BigDecimal price, String id) {
        return productPersistancePort.changePrice(price, id);
    }

}
