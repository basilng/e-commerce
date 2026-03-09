package com.bng.ecommerce.order.client.dto;



/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record UserResponse(String id,
                           String firstName,
                           String lastName,
                           String email,
                           String phone,
                           UserRole role,
                           AddressDTO address) {
}
