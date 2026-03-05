package com.bng.ecommerce.order.controller.dto;

import com.bng.ecommerce.order.model.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record OrderResponse(Long id,
                           BigDecimal totalAmount,
                           OrderStatus status,
                           List<OrderItemDTO> items,
                           LocalDateTime createdAt) {
}
