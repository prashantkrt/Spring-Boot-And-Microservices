package com.mylearning.rabbitmqpubsub.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    //Queue set karo
    @Bean
    public Queue queue() {
        return new Queue("paytm_queue");
    }

    //exchange banao
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("paytm_exchange");
    }

    //exchange me routing-key set karo aur bind karo exchange aur queue ko using routing-key
    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("paytm_routingKey");
    }


    //batoo data kaise exchange hoga
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    //template me daalao :))
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
