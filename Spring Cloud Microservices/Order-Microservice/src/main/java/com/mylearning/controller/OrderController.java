package com.mylearning.controller;

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
    public String placeOrder(@RequestBody Order order) {
      return orderService.createOrder(order);
    }

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable String orderId){
        return orderService.getOrder(orderId);
    }

}
