package com.mylearning.rabbitmqpubsub.service;

import com.mylearning.rabbitmqpubsub.dto.PaymentRequest;
import com.mylearning.rabbitmqpubsub.publisher.PaytmPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaytmPublisher paytmPublisher;

    public void publishPayment(PaymentRequest request) {
        paytmPublisher.publish(request);
    }

    public void sendRawMessage(String messageBody) {
        paytmPublisher.sendMessage(messageBody);
    }
}