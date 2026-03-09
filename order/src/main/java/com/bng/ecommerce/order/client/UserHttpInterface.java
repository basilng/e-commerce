package com.bng.ecommerce.order.client;

import com.bng.ecommerce.order.client.dto.UserResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 08/03/26
 ***/

@HttpExchange
public interface UserHttpInterface {

    @GetExchange("/api/users/{id}")
    UserResponse getUserById(@PathVariable String id);
}
