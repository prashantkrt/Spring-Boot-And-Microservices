package com.mylearning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.entity.Order;
import com.mylearning.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${order.producer.topic.name}")
    private String topicName;

    public String createOrder(Order order) {
        order.setOrderDate(new Date());
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        orderRepository.save(order);
        //Once order received it should be sent to payment service
        try {
            kafkaTemplate.send(topicName, new ObjectMapper().writeValueAsString(order));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
        return "Your Order created!!! with order id "+ order.getOrderId()+"...Once confirmed will notify you!!!";
    }

    public String getOrder(String orderId) {
        return null;
    }
}
