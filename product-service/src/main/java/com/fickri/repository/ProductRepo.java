package com.fickri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fickri.entity.Product;

public interface ProductRepo extends JpaRepository <Product, Long>{

}
