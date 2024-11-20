package com.mylearning.rabbitmqpubsub.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig2 {

    @Bean(name="rawMessageQueue2")
    public Queue rawMessageQueue2() {
        return new Queue("my-queue", true); // Durable queue
    }

    @Bean(name="rawMessageExchange2")
    public Exchange rawMessageExchange2() {
        return ExchangeBuilder.directExchange("my-exchange").durable(true).build();
    }

    @Bean(name="rawMessageBinding2")
    public Binding rawMessageBinding2(Queue rawMessageQueue2, Exchange rawMessageExchange2) {
        return BindingBuilder.bind(rawMessageQueue2).to(rawMessageExchange2).with("my-routing-key").noargs();
    }
}
