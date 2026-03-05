package com.bng.ecommerce.user.controller.dto;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record UserRequest(String username,
                          String firstName,
                          String lastName,
                          String password,
                          String email,
                          String phone,
                          AddressDTO address
) {
}
