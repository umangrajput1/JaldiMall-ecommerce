package com.jaldimall.repository;

import com.jaldimall.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Wishlist findByUser(Long userId);
}
