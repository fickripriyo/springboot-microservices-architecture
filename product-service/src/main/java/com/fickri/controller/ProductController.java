package com.fickri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fickri.entity.Product;
import com.fickri.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping
    public Product Save (@RequestBody Product product){
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public Product findById (@PathVariable("id") Long id){
        return productService.findById(id);
    }

    @GetMapping
    public Iterable<Product> findAll(){
        return productService.findAll();
    }

}
