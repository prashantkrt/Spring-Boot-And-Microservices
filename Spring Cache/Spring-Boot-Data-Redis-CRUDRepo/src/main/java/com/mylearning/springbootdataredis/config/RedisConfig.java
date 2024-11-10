package com.mylearning.springbootdataredis.config;

import com.mylearning.springbootdataredis.hash.Product;
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
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName("localhost");  // Redis host
        config.setPort(6379);            // Redis port
        return new LettuceConnectionFactory(config);
    }

    @Bean
    public RedisTemplate<String, Product> redisTemplate() {
        RedisTemplate<String, Product> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        //optional
        //Setting serializers
        template.setKeySerializer(new StringRedisSerializer()); //Serialize keys as strings
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //Serialize values as JSON
        return template;
    }











}


