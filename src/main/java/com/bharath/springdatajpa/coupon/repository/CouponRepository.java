package com.bharath.springdatajpa.coupon.repository;

import com.bharath.springdatajpa.coupon.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
