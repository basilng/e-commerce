package com.bng.ecommerce.order.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.spring6.circuitbreaker.configure.CircuitBreakerConfigurationProperties;
import org.springframework.boot.health.contributor.AbstractHealthIndicator;
import org.springframework.boot.health.contributor.CompositeHealthContributor;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Manual circuit breaker health contributor for Spring Boot 4 compatibility.
 * Resilience4j spring-boot3:2.3.0 uses the old org.springframework.boot.actuate.health.HealthIndicator
 * which was moved to org.springframework.boot.health.contributor in Spring Boot 4.
 */
@Configuration
public class CircuitBreakerHealthConfig {

    @Bean("circuitBreakers")
    public HealthContributor circuitBreakers(
            CircuitBreakerRegistry circuitBreakerRegistry,
            CircuitBreakerConfigurationProperties properties) {
        Map<String, HealthContributor> contributors = new LinkedHashMap<>();
        properties.getInstances().forEach((name, instanceProperties) -> {
            if (Boolean.TRUE.equals(instanceProperties.getRegisterHealthIndicator())) {
                CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker(name);
                contributors.put(name, new CircuitBreakerHealthIndicator(cb));
            }
        });
        return CompositeHealthContributor.fromMap(contributors);
    }

    static class CircuitBreakerHealthIndicator extends AbstractHealthIndicator {
        private final CircuitBreaker circuitBreaker;

        CircuitBreakerHealthIndicator(CircuitBreaker circuitBreaker) {
            this.circuitBreaker = circuitBreaker;
        }

        @Override
        protected void doHealthCheck(Health.Builder builder) {
            CircuitBreaker.State state = circuitBreaker.getState();
            CircuitBreaker.Metrics metrics = circuitBreaker.getMetrics();
            builder.withDetail("state", state.name())
                   .withDetail("bufferedCalls", metrics.getNumberOfBufferedCalls())
                   .withDetail("failedCalls", metrics.getNumberOfFailedCalls())
                   .withDetail("notPermittedCalls", metrics.getNumberOfNotPermittedCalls())
                   .withDetail("failureRate", metrics.getFailureRate());
            if (state == CircuitBreaker.State.OPEN) {
                builder.down();
            } else {
                builder.up();
            }
        }
    }
}
