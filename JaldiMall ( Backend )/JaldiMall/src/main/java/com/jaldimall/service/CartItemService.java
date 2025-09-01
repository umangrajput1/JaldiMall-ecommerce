package com.jaldimall.service;

import com.jaldimall.model.CartItem;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {

    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception;
    void removeCartItem(Long userId, Long cartItemId) throws Exception;
    CartItem findCartItemById(Long id) throws Exception;
}
