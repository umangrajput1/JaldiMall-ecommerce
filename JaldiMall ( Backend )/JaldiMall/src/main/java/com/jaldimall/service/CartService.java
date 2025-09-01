package com.jaldimall.service;

import com.jaldimall.model.Cart;
import com.jaldimall.model.CartItem;
import com.jaldimall.model.Product;
import com.jaldimall.model.User;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public CartItem addCardItem(
            User user,
            Product product,
            String size,
            int quantity
    );
    public Cart findUserCart(User user);
}
