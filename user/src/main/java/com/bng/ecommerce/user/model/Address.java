package com.bng.ecommerce.user.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
@Data
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
}
