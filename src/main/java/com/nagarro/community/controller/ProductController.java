package com.nagarro.community.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.community.dto.ProductDto;
import com.nagarro.community.dto.ReviewDto;
import com.nagarro.community.entity.Product;
import com.nagarro.community.service.ProductService;
import com.nagarro.community.service.ReviewService;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    ProductDto dto;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(path = "")
    public ResponseEntity<List<ProductDto>> getProducts() {
        try {
            List<ProductDto> productRes = service.getProducts();

            if (productRes.isEmpty()) {
                return new ResponseEntity<List<ProductDto>>(null, null, HttpStatus.NOT_FOUND);
            } else {
                for (int i = 0; i < productRes.size(); i++) {
                    productRes.get(i).setRating(getAvgRating(productRes.get(i).getId()));
                }
                return new ResponseEntity<List<ProductDto>>(productRes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<List<ProductDto>>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/brand/{brand}")
    public ResponseEntity<List<ProductDto>> getByBrand(@PathVariable String brand) {
        try {
            List<ProductDto> productRes = service.searchByBrand(brand);

            if (productRes.isEmpty()) {
                return new ResponseEntity<List<ProductDto>>(null, null, HttpStatus.NOT_FOUND);
            } else {
                for (int i = 0; i < productRes.size(); i++) {
                    productRes.get(i).setRating(getAvgRating(productRes.get(i).getId()));
                }
                return new ResponseEntity<List<ProductDto>>(productRes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<List<ProductDto>>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/name/{name}")
    public ResponseEntity<List<ProductDto>> getByName(@PathVariable String name) {
        try {
            List<ProductDto> productRes = service.searchByName(name);

            if (productRes.isEmpty()) {
                return new ResponseEntity<List<ProductDto>>(null, null, HttpStatus.NOT_FOUND);
            } else {
                for (int i = 0; i < productRes.size(); i++) {
                    productRes.get(i).setRating(getAvgRating(productRes.get(i).getId()));
                }
                return new ResponseEntity<List<ProductDto>>(productRes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<List<ProductDto>>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable int id) {
        try {
            ProductDto productRes = service.searchById(id);

            if (productRes == null) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            } else {
                productRes.setRating(getAvgRating(productRes.getId()));
                return new ResponseEntity<ProductDto>(productRes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/search/{value}")
    public ResponseEntity<List<ProductDto>> getByValue(@PathVariable String value) {
        try {
            List<ProductDto> productRes = service.searchByValue(value);

            if (productRes == null) {
                return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
            } else {
                for (int i = 0; i < productRes.size(); i++) {
                    productRes.get(i).setRating(getAvgRating(productRes.get(i).getId()));
                }
                return new ResponseEntity<List<ProductDto>>(productRes, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "")
    @RolesAllowed("USER")
    public ResponseEntity<ProductDto> addProduct(@RequestBody Product product) {
        try {
            ProductDto productRes = service.addProduct(product);
            if (productRes == null) {
                return new ResponseEntity<ProductDto>(null, null, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<ProductDto>(productRes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ProductDto>(null, null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private int getAvgRating(int id) {
        int rating = 0;
        List<ReviewDto> reviews = reviewService.getReviews(id);
        int totalReviews = reviews.size();
        if (totalReviews == 0)
            return -1;

        for (int i = 0; i < totalReviews; i++) {
            rating += reviews.get(i).getRating();
        }
        return rating / totalReviews;
    }

}