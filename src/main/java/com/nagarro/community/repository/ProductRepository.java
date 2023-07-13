package com.nagarro.community.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.community.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
    List<Product> findByNameContains(String name);
    List<Product> findByBrandContains(String brand);
    
    List<Product> findByNameContainsOrBrandContainsOrDescriptionContains(
    String name, 
    String brand,
    String description
);
}
