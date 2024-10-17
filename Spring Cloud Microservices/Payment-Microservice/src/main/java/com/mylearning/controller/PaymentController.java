package com.mylearning.controller;

import com.mylearning.entity.Payment;
import com.mylearning.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable String orderId) {
        log.info("Getting payment for orderId: {}", orderId);
        return paymentService.getByOrderId(orderId);
    }

}
