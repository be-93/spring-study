package cus.study.spring.coupon.domain;

import cus.study.spring.common.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CouponHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long couponId;
    private int beforeQuantity;
    private int afterQuantity;

    protected CouponHistory() {
    }

    public CouponHistory(Long couponId, int beforeQuantity, int afterQuantity) {
        this.couponId = couponId;
        this.beforeQuantity = beforeQuantity;
        this.afterQuantity = afterQuantity;
    }

    public Long getId() {
        return id;
    }

    public Long getCouponId() {
        return couponId;
    }

    public int getBeforeQuantity() {
        return beforeQuantity;
    }

    public int getAfterQuantity() {
        return afterQuantity;
    }
}
