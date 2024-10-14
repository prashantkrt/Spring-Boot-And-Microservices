package com.mylearning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mylearning.dto.OrderResponseDto;
import com.mylearning.entity.Order;
import com.mylearning.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public String placeOrder(@RequestBody Order order) throws JsonProcessingException {
      return orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable String orderId){
        return orderService.getOrder(orderId);
    }

}
