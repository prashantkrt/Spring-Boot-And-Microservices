package com.mylearning.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApiGatewayConfig {

//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
//                .route("order-service", r -> r.path("/orders/**")
//                        .and()
//                        .uri("lb://ORDER-MICROSERVICE"))
//                .route("payment-service", r -> r.path("/payments/**")
//                        .and()
//                        .uri("lb://PAYMENT-MICROSERVICE"))
//                .route("user-service", r -> r.path("/users/**")
//                        .and()
//                        .uri("lb://USER-MICROSERVICE"))
//                .build();
//    }

    @Bean
    public RestTemplate template(){
        return new RestTemplate();
    }
}
