package com.bng.ecommerce.order.client.dto;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
public record AddressDTO(String street,
                         String city,
                         String state,
                         String country,
                         String zipcode) {
}
