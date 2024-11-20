package com.mylearning.rabbitmqpubsub.controller;

import com.mylearning.rabbitmqpubsub.dto.PaymentRequest;
import com.mylearning.rabbitmqpubsub.entity.Payment;
import com.mylearning.rabbitmqpubsub.service.PaymentCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class PaymentCRUDController {
    @Autowired
    private PaymentCRUDService paymentService;

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody PaymentRequest paymentRequest) {
        Payment payment = new Payment();
        payment.setTransactionId(paymentRequest.getTransactionId());
        payment.setSourceAccount(paymentRequest.getSourceAccount());
        payment.setDestinationAccount(paymentRequest.getDestinationAccount());
        payment.setAmount(paymentRequest.getAmount());
        payment.setTransactionDate(paymentRequest.getTransactionDate());
        Payment savedPayment = paymentService.createPayment(payment);
        return ResponseEntity.ok(savedPayment);
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long id) {
        return new ResponseEntity<>(paymentService.getPaymentById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable Long id, @RequestBody PaymentRequest paymentRequest) {
        Payment paymentDetails = new Payment();
        paymentDetails.setTransactionId(paymentRequest.getTransactionId());
        paymentDetails.setSourceAccount(paymentRequest.getSourceAccount());
        paymentDetails.setDestinationAccount(paymentRequest.getDestinationAccount());
        paymentDetails.setAmount(paymentRequest.getAmount());
        paymentDetails.setTransactionDate(paymentRequest.getTransactionDate());
        Payment updatedPayment = paymentService.updatePayment(id, paymentDetails);
        return ResponseEntity.ok(updatedPayment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
