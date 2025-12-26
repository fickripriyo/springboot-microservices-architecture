package com.fickri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable findAll(){
        return productRepo.findAll();
    }
}


