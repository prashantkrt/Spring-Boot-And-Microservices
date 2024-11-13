package com.mylearning.redispubsub.config;

import com.mylearning.redispubsub.consumer.MessageSubscriber;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisMQConfig {

    //Create the topic or channel or queue
    @Bean
    public ChannelTopic channelTopic() {
        return new ChannelTopic("channelTopic");
    }

    // define the consumer in Adapter
    public MessageListenerAdapter messageListenerAdapter(MessageSubscriber subscriber) {
        return new MessageListenerAdapter(subscriber);
    }

    // set the values of adapter and topic in container
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory connectionFactory ) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListenerAdapter(), channelTopic());
        return container;
    }


    //optional
    @Bean
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }
}
