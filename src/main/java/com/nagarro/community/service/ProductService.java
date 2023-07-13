package com.nagarro.community.service;

import java.util.List;

import com.nagarro.community.dto.ProductDto;
import com.nagarro.community.entity.Product;

public interface ProductService {
    List<ProductDto> getProducts();
    List<ProductDto> searchByName(String name);
    List<ProductDto> searchByBrand(String brand);
    List<ProductDto> searchByValue(String value);
    ProductDto searchById(int id);
    ProductDto addProduct(Product product);
}
