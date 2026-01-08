package com.fickri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fickri.dto.ProductResponse;
import com.fickri.entity.Product;
import com.fickri.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public Product save(Product product){
        return productRepo.save(product);
    }

    public Product findById(Long id){
        return productRepo.findById(id).orElse(null);
    }

    // public Iterable findAll(){
    //     return productRepo.findAll();
    // }

    public Page<ProductResponse> findProducts(String name, Pageable pageable) {
        Page<Product> products;

        if (name == null || name.isBlank()) {
            products = productRepo.findAll(pageable);
        } else {
            products = productRepo
                    .findByNameContainingIgnoreCase(name, pageable);
        }

        return products.map(this::toResponse);
    }

     private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        ); 
    }

}


