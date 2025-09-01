package com.jaldimall.service.impl;

import com.jaldimall.model.Product;
import com.jaldimall.model.User;
import com.jaldimall.model.Wishlist;
import com.jaldimall.repository.WishlistRepository;
import com.jaldimall.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public Wishlist createWishlist(User user) {
        Wishlist wishlist =new Wishlist();
        wishlist.setUser(user);
        return wishlistRepository.save(wishlist);
    }

    @Override
    public Wishlist getWishlistByUserId(User user) {
        Wishlist wishlist = wishlistRepository.findByUser(user.getId());
        if (wishlist == null ){
            wishlist = createWishlist(user);
        }
        return wishlist;
    }

    @Override
    public Wishlist addProductToWishlist(User user, Product product) {
        Wishlist wishlist = getWishlistByUserId(user);
        if (wishlist.getProducts().contains(product)){
            wishlist.getProducts().remove(product);
        }
        else {
            wishlist.getProducts().add(product);
        }
        return wishlistRepository.save(wishlist);

    }
}
