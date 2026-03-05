package com.bng.ecommerce.order.repository;

import com.bng.ecommerce.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}