package com.mylearning.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.dto.OrderDto;
import com.mylearning.dto.UserDto;
import com.mylearning.entity.Payment;
import com.mylearning.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
@Slf4j
public class OrderProcessingConsumer {

    public static final String USER_SERVICE_URL = "http://localhost:9090/users/";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PaymentRepository paymentRepository;

    @KafkaListener(topics = "order-payment-topic", groupId = "payment-consumer-group")
    public void process(String orderJsonString) {
        try {
            OrderDto orderDto = new ObjectMapper().readValue(orderJsonString, OrderDto.class);

            //build payment request
            Payment payment = Payment.builder()
                    .payMode(orderDto.getPaymentMode())
                    .amount(orderDto.getPrice())
                    .paidDate(new Date())
                    .userId(orderDto.getUserId())
                    .orderId(orderDto.getOrderId())
                    .build();

            if (payment.getPayMode().equalsIgnoreCase("cod")) {
                payment.setPaymentStatus("pending");
            } else {
                UserDto userDto = restTemplate.getForObject(USER_SERVICE_URL + payment.getUserId(),UserDto.class);

                if(userDto.getAvailableAmount() < payment.getAmount()){
                    payment.setPaymentStatus("pending");
                    throw new RuntimeException("Insufficient amount !");
                }
                else{
                    restTemplate.put(USER_SERVICE_URL  + payment.getUserId() + "/" + payment.getAmount(), null);
                    payment.setPaymentStatus("success");
                }
            }
            paymentRepository.save(payment);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
