package com.hexagonal.hexagonal.infrastructure.adapters.in.web.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexagonal.hexagonal.application.ports.in.ProductServicePort;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateProductCommand;
import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.dto.ProductDto;
import com.hexagonal.hexagonal.infrastructure.mapper.ProductMapper;



@RestController
@RequestMapping("/api/products/")
public class ProductController {

    private final ProductServicePort productServicePort;
    private final ProductMapper productMapper;

    public ProductController(ProductServicePort productServicePort, ProductMapper productMapper) {
        this.productServicePort = productServicePort;
        this.productMapper = productMapper;
    }

    // From domain entities to Dto
    @GetMapping
    public ResponseEntity<List<ProductDto>> listAll() {
        return ResponseEntity.ok(productMapper.fromDomainListToDtoList(productServicePort.listAll()));
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable String id) {
        return ResponseEntity.ok(productMapper.fromDomainToDto(productServicePort.findbyId(id)));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProducEntity(@RequestBody CreateProductCommand command) {
        return ResponseEntity.ok(productMapper.fromDomainToDto(productServicePort.createProduct(command)));
    }

    @PutMapping("{id}/{price}")
    public ResponseEntity<ProductDto> addPrice(@PathVariable BigDecimal price, @PathVariable String id) {
        return ResponseEntity.ok(productMapper.fromDomainToDto(productServicePort.changePrice(price, id)));
    }

}