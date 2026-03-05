package com.bng.ecommerce.user.controller.dto;

import com.bng.ecommerce.user.model.UserRole;

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
