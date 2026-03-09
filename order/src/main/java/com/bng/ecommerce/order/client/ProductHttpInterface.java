package com.bng.ecommerce.order.client;

import com.bng.ecommerce.order.client.dto.ProductResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 08/03/26
 ***/

@HttpExchange
public interface ProductHttpInterface {

    @GetExchange("/api/products/{id}")
    ProductResponse getProductResponse(@PathVariable String id);
}
