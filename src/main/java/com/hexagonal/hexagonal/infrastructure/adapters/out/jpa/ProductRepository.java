package com.hexagonal.hexagonal.infrastructure.adapters.out.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexagonal.hexagonal.infrastructure.adapters.out.jpa.entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

}
