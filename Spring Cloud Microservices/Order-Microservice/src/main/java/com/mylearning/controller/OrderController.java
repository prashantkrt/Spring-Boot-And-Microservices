package com.mylearning.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mylearning.dto.OrderResponseDto;
import com.mylearning.entity.Order;
import com.mylearning.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public String placeOrder(@RequestBody Order order) throws JsonProcessingException {
        log.info("Order Placed : {}", order);
        return orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public OrderResponseDto getOrder(@PathVariable String orderId) {
        log.info("Get Order : {}", orderId);
        return orderService.getOrder(orderId);
    }

}
