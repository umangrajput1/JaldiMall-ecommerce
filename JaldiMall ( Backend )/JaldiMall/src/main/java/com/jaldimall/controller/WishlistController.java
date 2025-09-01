package com.jaldimall.controller;

import com.jaldimall.exception.ProductException;
import com.jaldimall.model.Product;
import com.jaldimall.model.User;
import com.jaldimall.model.Wishlist;
import com.jaldimall.service.ProductService;
import com.jaldimall.service.UserService;
import com.jaldimall.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWithList(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        Product product = productService.findProductById(productId);
        User user = userService.findByJwtToken(jwt);

        Wishlist updatedWishlist = wishlistService.addProductToWishlist(user, product);

        return ResponseEntity.ok(updatedWishlist);


    }
}
