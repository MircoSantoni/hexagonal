package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexagonal.hexagonal.application.ports.exceptions.ListDoesNotExistException;
import com.hexagonal.hexagonal.application.ports.out.ProductPersistancePort;
import com.hexagonal.hexagonal.domain.model.Product;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateProductCommand;
import com.hexagonal.hexagonal.infrastructure.exceptions.EmptyResourceException;
import com.hexagonal.hexagonal.infrastructure.mapper.ProductMapper;

@Service
public class ProductJpaAdapter implements ProductPersistancePort {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductJpaAdapter(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }    

    @Override
    public List<Product> listAll() {
        try {
            return productRepository.findAll().stream()
                    .map(productMapper::fromEntityToDomain)
                    .toList();
        } catch (Exception ex) {
            throw new ListDoesNotExistException("Error al cargar la lista de clientes");
        }
    }

    @Override
    public Product findbyId(String id) {
        return productRepository.findById(id).map(productMapper::fromEntityToDomain)
                .orElseThrow(() -> new EmptyResourceException("No existe ningun producto con ese id"));
    }

    @Override
    public Product createProduct(CreateProductCommand command) {
        try {
            var entity = productMapper.fromCommandToEntity(command);
            var savedEntity = productRepository.save(entity);
            
            return productMapper.fromEntityToDomain(savedEntity);
        } catch (Exception e) {
        }
        throw new RuntimeException("Excepcion general al crear un producto");
    }

    @Override
    public Product changePrice(BigDecimal price, String id) {
        var dbEntity = productRepository.findById(id)
                .orElseThrow(() -> new EmptyResourceException("No existe ningun producto con ese id"));

        try {
            dbEntity.setPrice(price.add(dbEntity.getPrice()));
            var savedEntity = productRepository.save(dbEntity);
            
            return productMapper.fromEntityToDomain(savedEntity);
        } catch (Exception e) {
            throw new RuntimeException("Excepcion general al cambiar un precio");
        }

    }


}
