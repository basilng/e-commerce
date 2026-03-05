package com.bng.ecommerce.user.model;

import lombok.Data;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
@Data
public class Address {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
