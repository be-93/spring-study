package cus.study.spring.coupon.domain;

import cus.study.spring.common.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Coupon extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;
    private BigDecimal discountAmount;

    protected Coupon() {
    }

    public Coupon(int quantity, BigDecimal discountAmount) {
        this.quantity = quantity;
        this.discountAmount = discountAmount;
    }

    public void deduct() {
        if (quantity < 1) {
            throw new RuntimeException("쿠폰이 모두 소진되었습니다.");
        }
        quantity--;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

}
