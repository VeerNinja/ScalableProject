package com.order.service;

import org.springframework.stereotype.Service;

import com.order.dto.User;
import com.order.entity.Order;
import com.order.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserClient userClient;

    public OrderService(OrderRepository orderRepository, UserClient userClient) {
        this.orderRepository = orderRepository;
        this.userClient = userClient;
    }

    public Order createOrder(Order order) {
        // Validate user before creating order
        User user = userClient.getUserById(order.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found!");
        }

        order.setStatus("CREATED");
        return orderRepository.save(order);
    }
}


