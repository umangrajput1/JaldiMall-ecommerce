package com.jaldimall.repository;

import com.jaldimall.model.Cart;
import com.jaldimall.model.CartItem;
import com.jaldimall.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);
}
