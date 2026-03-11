package com.bng.ecommerce.order.controller;

import com.bng.ecommerce.order.controller.dto.CartItemRequest;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 11/03/26
 ***/

//to show the demo of rate limiting
@RestController
@RequestMapping
public class MessageController {


    @GetMapping("/message")
    @RateLimiter(name = "rateBreaker", fallbackMethod = "fallbackRateLimiter")
    public String message() {
        return "Hello world";
    }


    public String fallbackRateLimiter(Exception exception) {
        String fallbackCalled = "FALLBACK CALLED";
        System.out.println(fallbackCalled);
        exception.printStackTrace();
        return fallbackCalled;
    }
}
