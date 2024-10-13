package com.mylearning.service;

import com.mylearning.entity.Order;
import com.mylearning.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${order.producer.topic.name}")
    private String topicName;

    public String createOrder(Order order) {
        order.setOrderDate(new Date());
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        orderRepository.save(order);
        //Once order received it should be sent to payment service
        kafkaTemplate.send(topicName, order);
        return "Your Order created!!! with order id "+ order.getOrderId()+"...Once confirmed will notify you!!!";
    }

    public String getOrder(String orderId) {
        return null;
    }
}
