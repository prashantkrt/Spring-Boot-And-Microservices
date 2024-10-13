package com.mylearning.service;

import com.mylearning.entity.Payment;
import com.mylearning.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getByOrderId(String orderId) {
        return paymentRepository.findByOrderId(orderId);
    }

}
