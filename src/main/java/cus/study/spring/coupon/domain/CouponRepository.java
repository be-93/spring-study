package cus.study.spring.coupon.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    @Transactional
    @Modifying
    @Query("update Coupon c set c.quantity = c.quantity - 1 where c.id = :couponId and c.quantity > 0")
    int deductCoupon(@Param("couponId") Long couponId);
}
