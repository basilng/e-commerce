package com.bng.ecommerce.product.controller.dto;


import java.math.BigDecimal;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record ProductResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stockQuantity,
        String category,
        String imageUrl,
        Boolean active
) {
}
