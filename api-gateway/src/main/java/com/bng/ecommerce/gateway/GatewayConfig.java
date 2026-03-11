package com.bng.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 10/03/26
 ***/

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("product-service",
                        r -> r
                                .path("/api/products/**")
                                .filters( f -> f
                                        .retry(retryConfig ->  retryConfig
                                                .setRetries(10)
                                                .setMethods(HttpMethod.GET)
                                        )
                                        .circuitBreaker(config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/products")
                                ))
//                                .filters(f -> f
//                                        .rewritePath("/products(?<segment>/?.*)",
//                                                "/api/products${segment}"))
                                .uri("lb://PRODUCT-SERVICE"))
                .route("user-service",
                        r -> r
                                .path("/api/users/**")
                                .filters( f -> f.circuitBreaker(config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/users")
                                ))
//                                .filters(f -> f
//                                        .rewritePath("/users(?<segment>/?.*)",
//                                                "/api/users${segment}"))
                                .uri("lb://USER-SERVICE"))
                .route("order-service",
                        r -> r
                                .path("/api/orders/**", "/api/cart/**")
                                .filters( f -> f.circuitBreaker(config -> config
                                        .setName("ecomBreaker")
                                        .setFallbackUri("forward:/fallback/orders")
                                ))
//                                .filters(f -> f
//                                        .rewritePath("/(?<segment>.*)",
//                                                "/api/${segment}"))
                                .uri("lb://ORDER-SERVICE"))
                .route("eureka",
                        r -> r
                                .path("/**")
                                .uri("http://localhost:8761"))
                .build();
    }
}
