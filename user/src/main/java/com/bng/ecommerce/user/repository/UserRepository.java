package com.bng.ecommerce.user.repository;

import com.bng.ecommerce.user.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Project: IntelliJ IDEA
 * Author: bng-mac
 * Date: 05/03/26
 ***/
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
