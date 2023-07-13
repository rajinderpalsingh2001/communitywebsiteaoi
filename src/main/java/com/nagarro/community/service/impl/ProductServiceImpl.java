package com.nagarro.community.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.community.dto.ProductDto;
import com.nagarro.community.entity.Product;
import com.nagarro.community.repository.ProductRepository;
import com.nagarro.community.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    ProductDto dto;

    @Override
    public List<ProductDto> searchByName(String name) {
        return dto.toObj(repo.findByNameContains(name));
    }

    @Override
    public List<ProductDto> searchByBrand(String brand) {
        return dto.toObj(repo.findByBrandContains(brand));
    }

    @Override
    public ProductDto searchById(int id) {
        Product product = repo.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return dto.toObj(product);
    }

    @Override
    public ProductDto addProduct(Product product) {
        Product productFind = repo.findById(product.getId()).orElse(null);
        if (product.getId() == 0 || productFind == null) {
            // no id assigned
            return dto.toObj(repo.save(product));
        }
        return null;
        // return dto.toObj(repo.save(product));
    }

    @Override
    public List<ProductDto> getProducts() {
        return dto.toObj((List<Product>) repo.findAll());
    }

    @Override
    public List<ProductDto> searchByValue(String value) {
        return dto.toObj(repo.findByNameContainsOrBrandContainsOrDescriptionContains(value, value, value));
    }
}
