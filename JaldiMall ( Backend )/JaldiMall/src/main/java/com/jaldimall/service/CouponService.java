package com.jaldimall.service;

import com.jaldimall.model.Cart;
import com.jaldimall.model.Coupon;
import com.jaldimall.model.User;

import java.util.List;

public interface CouponService {

    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getALlCoupon();
    void deleteCoupon(Long id) throws Exception;
}
