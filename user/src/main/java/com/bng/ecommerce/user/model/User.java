package com.bng.ecommerce.user.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
@Data
@Entity(name = "users")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    private String firstName;
    private String lastName;

    private String email;
    private String phone;
    private UserRole role = UserRole.CUSTOMER;
    @Embedded
    private Address address;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
