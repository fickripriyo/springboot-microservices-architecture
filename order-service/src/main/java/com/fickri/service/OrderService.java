package com.fickri.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fickri.dto.OrderLineResponse;
import com.fickri.dto.OrderResponse;
import com.fickri.dto.Product;
import com.fickri.entity.Order;
import com.fickri.entity.OrderLine;
import com.fickri.repository.OrderRepo;
import com.fickri.webclient.CustomerClient;
import com.fickri.webclient.ProductClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    public Order save(Order order){
        for (OrderLine orderLine : order.getOrderLines()){
            orderLine.setOrder(order);
        }
        return orderRepo.save(order);
    }

    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackFindCustomerById")
    public OrderResponse findById(Long id){
        Optional<Order> optOrder = orderRepo.findById(id);
        if(!optOrder.isPresent()){
            return null;
        }
        
        Order order = optOrder.get();
        OrderResponse response = new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), customerClient.findById(order.getCustomerId()), new ArrayList<OrderLineResponse>());

        for(OrderLine orderLine : order.getOrderLines()){
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(), orderLine.getPrice()));
        }


        return response;
    }

    private OrderResponse fallbackFindCustomerById(Long id, Throwable throwable){
        return new OrderResponse();
    }

    public OrderResponse findByOrderNumber(String orderNumber){
        Order order = orderRepo.findByOrderNumber(orderNumber);
        if(order == null ){
            return null;
        }
        OrderResponse response = new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(), customerClient.findById(order.getCustomerId()), new ArrayList<OrderLineResponse>());

        for(OrderLine orderLine : order.getOrderLines()){
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(), orderLine.getPrice()));
        }


        return response;
    }

    // public Customer findCustomerById(Long id){
    //     return restTemplate.getForObject("http://CUSTOMER-SERVICE/api/customers/" + id, Customer.class);
    // }

    // public Product finProductById(Long id){
    //     return restTemplate.getForObject("http://PRODUCT-SERVICE/api/products/" + id, Product.class);
    // }
}
