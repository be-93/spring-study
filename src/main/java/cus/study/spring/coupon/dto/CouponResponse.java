package cus.study.spring.coupon.dto;

import cus.study.spring.coupon.domain.Coupon;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CouponResponse {
    private Long id;
    private int quantity;
    private BigDecimal discountAmount;
    private LocalDateTime createDate;

    public CouponResponse(Long id, int quantity, BigDecimal discountAmount, LocalDateTime createDate) {
        this.id = id;
        this.quantity = quantity;
        this.discountAmount = discountAmount;
        this.createDate = createDate;
    }

    public static CouponResponse of(Coupon coupon) {
        return new CouponResponse(coupon.getId(), coupon.getQuantity(), coupon.getDiscountAmount(), coupon.getCreatedDate());
    }
}
