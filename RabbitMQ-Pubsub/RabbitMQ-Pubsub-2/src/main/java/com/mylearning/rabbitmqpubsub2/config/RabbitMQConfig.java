package com.mylearning.rabbitmqpubsub2.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Queue 1
    @Bean
    public Queue queue1() {
        return new Queue("queue1", true);  // Durable queue
    }

    // Queue 2
    @Bean
    public Queue queue2() {
        return new Queue("queue2", true);  // Durable queue
    }

    // Exchange 1
    @Bean
    public TopicExchange exchange1() {
        return new TopicExchange("exchange1");
    }

    // Exchange 2
    @Bean
    public TopicExchange exchange2() {
        return new TopicExchange("exchange2");
    }

    // Binding queue1 to exchange1 with routing key
    @Bean
    public Binding binding1(Queue queue1, TopicExchange exchange1) {
        return new Binding("queue1", Binding.DestinationType.QUEUE, "exchange1", "routing.key1", null);
    }

    // Binding queue2 to exchange2 with routing key
    @Bean
    public Binding binding2(Queue queue2, TopicExchange exchange2) {
        return new Binding("queue2", Binding.DestinationType.QUEUE, "exchange2", "routing.key2", null);
    }
}

//public Binding(String destination,
//               Binding.DestinationType destinationType,
//               String exchange,
//               String routingKey,
//               @Nullable Map<String, Object> arguments)
