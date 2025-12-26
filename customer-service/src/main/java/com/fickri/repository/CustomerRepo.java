package com.fickri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fickri.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

}
