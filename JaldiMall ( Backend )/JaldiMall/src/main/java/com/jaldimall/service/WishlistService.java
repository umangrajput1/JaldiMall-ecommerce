package com.jaldimall.service;

import com.jaldimall.model.Product;
import com.jaldimall.model.User;
import com.jaldimall.model.Wishlist;

public interface WishlistService {

    Wishlist createWishlist(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishlist(User user, Product product);
}
