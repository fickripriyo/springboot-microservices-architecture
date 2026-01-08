package com.fickri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fickri.entity.Product;
import com.fickri.service.ProductService;
import com.fickri.dto.ProductResponse;

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

    // @GetMapping
    // public Iterable<Product> findAll(){
    //     return productService.findAll();
    // }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> findProducts(@RequestParam(required = false) String name, @RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "12")int size){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(productService.findProducts(name, pageable));
    }

}
