package com.bng.ecommerce.order.service;

import com.bng.ecommerce.order.client.ProductHttpInterface;
import com.bng.ecommerce.order.client.UserHttpInterface;
import com.bng.ecommerce.order.client.dto.ProductResponse;
import com.bng.ecommerce.order.client.dto.UserResponse;
import com.bng.ecommerce.order.controller.dto.CartItemRequest;
import com.bng.ecommerce.order.model.CartItem;
import com.bng.ecommerce.order.repository.CartItemRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    private final CartItemRepository cartItemRepository;
    //    private final ProductRestClient restClient;
    private final ProductHttpInterface productHttpInterface;
    private final UserHttpInterface userHttpInterface;
    int attempt = 0;


    @CircuitBreaker(name = "productService", fallbackMethod = "addToCartFallBack")
    public boolean addToCart(String userId, CartItemRequest request) {
        System.out.println("ATTEMPT COUNT: " + ++attempt);
        // Look for product

        ProductResponse productResponse = productHttpInterface.getProductResponse(request.productId());
        if (productResponse == null || productResponse.stockQuantity() < request.quantity()) {
            return false;
        }

        UserResponse user = userHttpInterface.getUserById(userId);
        if (user == null) {
            return false;
        }


        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.productId());
        if (existingCartItem != null) {
            // Update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.quantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.productId());
            cartItem.setQuantity(request.quantity());
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }
        return true;
    }

    // this is the fallback method written. it should have the same parameter of the method which is using it
    // additionally exception
    public boolean addToCartFallBack(String userId,
                                     CartItemRequest request,
                                     Exception exception) {
        System.out.println("FALLBACK CALLED");
        exception.printStackTrace();
        return false;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (cartItem != null) {
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }

    public List<CartItem> getCart(String userId) {
        return cartItemRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}