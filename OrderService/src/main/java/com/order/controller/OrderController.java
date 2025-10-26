package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.User;
import com.order.entity.Order;
import com.order.repository.OrderRepository;
import com.order.service.UserClient;

import feign.FeignException;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;
  
  @Autowired
  private UserClient userClient;  // Feign Client


  @PostMapping("/createOrder")
  public ResponseEntity<Order> createOrder(@RequestBody Order order) {
      try {
          // 1️⃣ Check if user exists
          User user = userClient.getUserById(order.getUserId());
          if (user == null) {
              return ResponseEntity.badRequest().build();
          }

          // 2️⃣ Save order
          order.setStatus("PLACED");
          Order savedOrder = orderRepository.save(order);

          return ResponseEntity.ok(savedOrder);

      } catch (FeignException.NotFound e) {
          return ResponseEntity.badRequest().build();
      } catch (Exception e) {
          return ResponseEntity.internalServerError().build();
      }
  }


  @GetMapping("/user/{userId}")
  public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId) {
    return ResponseEntity.ok(orderRepository.findByUserId(userId));
  }
  
  @GetMapping("/getAllOrders")
  public ResponseEntity<List<Order>> getAllOrders() {
      return ResponseEntity.ok(orderRepository.findAll());
  }
}
