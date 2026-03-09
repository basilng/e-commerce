/*
package com.bng.ecommerce.order.client;

import com.bng.ecommerce.order.client.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

*/
/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 08/03/26
 ***//*

@Service
@RequiredArgsConstructor
public class ProductRestClient {

    private final RestClient restClient;

    public ProductResponse getProductResponse(String id) {
        return restClient.get()
                .uri("/api/products/" + id)
                .retrieve()
                .body(ProductResponse.class);
    }
}
*/
