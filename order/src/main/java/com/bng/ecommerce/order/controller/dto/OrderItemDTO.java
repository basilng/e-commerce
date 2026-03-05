package com.bng.ecommerce.order.controller.dto;

import java.math.BigDecimal;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record OrderItemDTO(Long id,
                           String productId,
                           Integer quantity,
                           BigDecimal price,
                           BigDecimal subTotal) {
}
