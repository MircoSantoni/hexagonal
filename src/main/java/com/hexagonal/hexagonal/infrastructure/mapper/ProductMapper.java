package com.hexagonal.hexagonal.infrastructure.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.hexagonal.hexagonal.domain.model.Product;
import com.hexagonal.hexagonal.infrastructure.adapters.in.web.dto.CreateProductCommand;
import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.dto.ProductDto;
import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.entities.ProductEntity;

@Mapper(componentModel="spring")
public interface ProductMapper {

    ProductDto fromDomainToDto (Product product);

    List<ProductDto> fromDomainListToDtoList(List<Product> product);

    Product fromEntityToDomain(ProductEntity productEntity);

    ProductEntity fromCommandToEntity(CreateProductCommand createProductCommand);

}
