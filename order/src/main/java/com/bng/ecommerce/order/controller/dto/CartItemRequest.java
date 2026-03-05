package com.bng.ecommerce.order.controller.dto;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record CartItemRequest(String productId,
                              Integer quantity) {
}
