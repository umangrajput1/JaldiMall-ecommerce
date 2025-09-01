package com.jaldimall.repository;

import com.jaldimall.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {


    Coupon findByCode(String code);
}
