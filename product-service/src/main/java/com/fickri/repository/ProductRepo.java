package com.fickri.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.fickri.entity.Product;

public interface ProductRepo extends JpaRepository <Product, Long>{

     Page<Product> findByNameContainingIgnoreCase(
            String name,
            Pageable pageable
    );

}
