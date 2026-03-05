package com.bng.ecommerce.order.service;

import com.bng.ecommerce.order.controller.dto.CartItemRequest;
import com.bng.ecommerce.order.model.CartItem;
import com.bng.ecommerce.order.repository.CartItemRepository;
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
    int attempt = 0;


    public boolean addToCart(String userId, CartItemRequest request) {
        System.out.println("ATTEMPT COUNT: " + ++attempt);
        // Look for product




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

    public boolean addToCartFallBack(String userId,
                                     CartItemRequest request,
                                     Exception exception) {
        exception.printStackTrace();
        return false;
    }

    public boolean deleteItemFromCart(String userId, String productId) {
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (cartItem != null){
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