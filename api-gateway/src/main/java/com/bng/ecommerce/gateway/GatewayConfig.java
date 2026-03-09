package com.bng.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                        r -> r.path("/api/products/**")
                                .uri("lb://PRODUCT-SERVICE"))
                .route("user-service",
                        r -> r.path("/api/users/**", "/api/users")
                                .uri("lb://USER-SERVICE"))
                .route("order-service",
                        r -> r.path("/api/orders/**","/api/cart/**")
                                .uri("lb://ORDER-SERVICE"))
                .route("eureka",
                        r -> r.path("/**")
                                .uri("http://localhost:8761"))
                .build();
    }
}
