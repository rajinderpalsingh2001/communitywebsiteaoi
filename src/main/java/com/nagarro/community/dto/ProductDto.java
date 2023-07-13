package com.nagarro.community.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.nagarro.community.entity.Product;

@Component
public class ProductDto {
    int id;
    
    String name;
    String brand;
    String description;
    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    int rating =-1;



    public int getRating() {
        return rating;
    }



    public void setRating(int rating) {
        this.rating = rating;
    }



    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getBrand() {
        return brand;
    }



    public void setBrand(String brand) {
        this.brand = brand;
    }



    public List<ProductDto> toObj(List<Product> products){
        List<ProductDto> result = new ArrayList<>();

        for(Product p : products){
            ProductDto dto = new ProductDto();
            dto.setId(p.getId());
            dto.setName(p.getName());
            dto.setDescription(p.getDescription());
            dto.setBrand(p.getBrand());
            result.add(dto);
        }

        return result;
    }
     
    public ProductDto toObj(Product product){
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setBrand(product.getBrand());
        return dto;
    }
}
