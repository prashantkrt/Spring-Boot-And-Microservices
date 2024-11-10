package com.mylearning.springbootdataredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        // Set the serializers
        template.setKeySerializer(new StringRedisSerializer()); // Serialize keys as Strings
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // Serialize values as JSON

        return template;
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Configure your Redis connection here, for example using RedisStandaloneConfiguration
        return new LettuceConnectionFactory("localhost", 6379); // Default Redis configuration
    }
}


