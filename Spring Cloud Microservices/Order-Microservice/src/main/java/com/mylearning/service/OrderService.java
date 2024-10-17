package com.mylearning.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylearning.dto.OrderResponseDto;
import com.mylearning.dto.PaymentDto;
import com.mylearning.dto.UserDto;
import com.mylearning.entity.Order;
import com.mylearning.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.UUID;
/*
* @RefreshScope annotation in Spring Cloud is used in applications to refresh beans dynamically at runtime without restarting the entire application.
* Only the beans with @RefreshScope are refreshed, and other beans remain unchanged.
 * */
@Service
@Slf4j
@RefreshScope // any changes to config server value will be reflected here
public class OrderService {

    private final String PAYMENT_URL = "http://PAYMENT-MICROSERVICE/payments/";
    private final String USER_URL = "http://USER-MICROSERVICE/users/";

//    @Value("${microservice.payment-service.endpoints.fetchPaymentById.uri}")
//    private String fetchPaymentUri;
//
//    @Value("${microservice.user-service.endpoints.fetchUserById.uri}")
//    private String fetchUserUri;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    @Lazy
    private RestTemplate restTemplate;
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
        return "Your Order created!!! with order id " + order.getOrderId() + "...Once confirmed will notify you!!!";
    }


    // we should be using circuit breaker when service methods call another rest api of external microservice
    @CircuitBreaker(name ="orderService" ,fallbackMethod = "getOrderDetails")
    public OrderResponseDto getOrder(String orderId) {
        Order order = orderRepository.findByOrderId(orderId);

        //PAYMENT-> REST call payment-service
        PaymentDto paymentDTO = restTemplate.getForObject(PAYMENT_URL + order.getOrderId(), PaymentDto.class);

        //user-info-> rest call user-service
        UserDto userDTO = restTemplate.getForObject(USER_URL + order.getUserId(), UserDto.class);

        return OrderResponseDto.builder()
                .message("Order Details")
                .order(order)
                .paymentResponse(paymentDTO)
                .userInfo(userDTO)
                .build();
    }
    public OrderResponseDto getOrderDetails(String orderId, Exception ex) {
        //you can call a DB
        //you can invoke external api
        return new OrderResponseDto("FAILED", null, null, null);
    }

}
