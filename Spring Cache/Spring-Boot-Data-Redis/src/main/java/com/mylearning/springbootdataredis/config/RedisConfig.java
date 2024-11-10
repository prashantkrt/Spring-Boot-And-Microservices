package com.mylearning.springbootdataredis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

    // Bean for RedisConnectionFactory
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // You can configure Lettuce or Jedis Connection Factory depending on your Redis client.

        // return new LettuceConnectionFactory("localhost", 6379);  // Adjust Redis host and port as needed
        // return new JedisConnectionFactory(); // using default


        // Create RedisStandaloneConfiguration with host and port

        //RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        //configuration.setHostName("localhost"); // Redis server host
        //configuration.setPort(6379);           // Redis server port
        // You can also configure other properties such as password or database
        // configuration.setPassword(RedisPassword.of("yourpassword"));
        // configuration.setDatabase(0);  // Default is 0
        //return new LettuceConnectionFactory(configuration);  // Create LettuceConnectionFactory using the configuration

        return new LettuceConnectionFactory(); // using default
    }









}



//RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//configuration.setHostName("localhost");
//configuration.setPort(6379);
//configuration.setPassword(RedisPassword.of("yourpassword"));  // Optional password
//configuration.setDatabase(1);  // Optional: Choose a different database (default is 0)

/*

or
application.yml or properties:

properties
Copy code
spring.redis.host=localhost
spring.redis.port=6379
spring.redis.password=yourpassword  # Optional if Redis requires authentication
spring.redis.database=0  # Optional; specify which Redis database to use (default is 0)

*/
