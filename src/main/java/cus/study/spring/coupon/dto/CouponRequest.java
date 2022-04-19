package cus.study.spring.coupon.dto;

import cus.study.spring.coupon.domain.Coupon;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CouponRequest {
    private int quantity;
    private BigDecimal discountAmount;

    private CouponRequest(int quantity, BigDecimal discountAmount) {
        this.quantity = quantity;
        this.discountAmount = discountAmount;
    }

    public static CouponRequest of(int quantity, BigDecimal discountAmount) {
        return new CouponRequest(quantity, discountAmount);
    }

    public Coupon toCoupon() {
        return new Coupon(quantity, discountAmount);
    }
}
