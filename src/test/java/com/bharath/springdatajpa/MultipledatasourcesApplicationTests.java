package com.bharath.springdatajpa;

import com.bharath.springdatajpa.coupon.entities.Coupon;
import com.bharath.springdatajpa.coupon.repository.CouponRepository;
import com.bharath.springdatajpa.product.entities.Product;
import com.bharath.springdatajpa.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class MultipledatasourcesApplicationTests {

    @Autowired
    CouponRepository couponRepository;

    @Autowired
    ProductRepository productRepository;

    @Test
    void testSaveCoupon() {
        Coupon coupon = new Coupon();
        coupon.setCode("SUPERSALE");
        coupon.setDiscount(new BigDecimal(20));
        coupon.setExpDate("22/12/2022");
        System.out.println(couponRepository.save(coupon));

    }

    @Test
    void testSaveProduct() {
        Product product = new Product();
        product.setName("Mac Book Pro");
        product.setDescription("It's cool!");
        product.setPrice(new BigDecimal(2000));
        product.setCouponCode("SUPERSALE");
        System.out.println(productRepository.save(product));
    }

}
