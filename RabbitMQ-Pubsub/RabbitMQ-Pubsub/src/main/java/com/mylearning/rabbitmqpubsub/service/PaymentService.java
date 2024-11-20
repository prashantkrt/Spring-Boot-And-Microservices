package com.mylearning.rabbitmqpubsub.service;

import com.mylearning.rabbitmqpubsub.entity.Payment;
import com.mylearning.rabbitmqpubsub.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(()->new RuntimeException("No Payment found"));
    }

    public Payment updatePayment(Long paymentId, Payment paymentDetails) {
        Payment existingPayment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
        existingPayment.setTransactionId(paymentDetails.getTransactionId());
        existingPayment.setSourceAccount(paymentDetails.getSourceAccount());
        existingPayment.setDestinationAccount(paymentDetails.getDestinationAccount());
        existingPayment.setAmount(paymentDetails.getAmount());
        existingPayment.setTransactionDate(paymentDetails.getTransactionDate());
        return paymentRepository.save(existingPayment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }


}
