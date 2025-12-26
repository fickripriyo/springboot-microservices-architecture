package com.fickri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fickri.dto.OrderResponse;
import com.fickri.entity.Order;
import com.fickri.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }

    @GetMapping("/order-number/{number}")
    public OrderResponse findByOrderNumber(@PathVariable("number") String number){
        return orderService.findByOrderNumber(number);
    }

    @PostMapping
    public Order save (@RequestBody Order order){
        return orderService.save(order);
    } 

}
