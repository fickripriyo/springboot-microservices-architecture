package com.fickri.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fickri.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long>{

    Order findByOrderNumber (String orderNumber);

}
