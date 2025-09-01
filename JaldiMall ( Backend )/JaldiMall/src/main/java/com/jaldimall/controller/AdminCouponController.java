package com.jaldimall.controller;

import com.jaldimall.model.Cart;
import com.jaldimall.model.Coupon;
import com.jaldimall.model.User;
import com.jaldimall.service.CartService;
import com.jaldimall.service.CouponService;
import com.jaldimall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/coupons")
public class AdminCouponController {

    private final CouponService couponService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/apply")
    public ResponseEntity<Cart> applyCoupon(
            @RequestParam String apply,
            @RequestParam String code,
            @RequestParam double orderValue,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findByJwtToken(jwt);
        Cart cart;

        if (apply.equals("true")){
            cart = couponService.applyCoupon(code, orderValue, user);
        }else {
            cart = couponService.removeCoupon(code, user);
        }
        return ResponseEntity.ok(cart);
    }

    // Admin Operations

    @PostMapping("/admin/create")
    public ResponseEntity<Coupon> createCoupon(@RequestBody Coupon coupon){
        Coupon createCoupon = couponService.createCoupon(coupon);
        return ResponseEntity.ok(createCoupon);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<?> deleteCoupon( @PathVariable Long id) throws Exception {
        couponService.deleteCoupon(id);
        return ResponseEntity.ok("coupon deleted successfully");
    }

    @GetMapping("/admin/all")
    public ResponseEntity<List<Coupon>> getAllCoupons(){
        List<Coupon> coupons = couponService.getALlCoupon();
        return ResponseEntity.ok(coupons);
    }
}
