package cus.study.spring.coupon.application;

import cus.study.spring.coupon.domain.Coupon;
import cus.study.spring.coupon.domain.CouponRepository;
import cus.study.spring.coupon.dto.CouponRequest;
import cus.study.spring.coupon.dto.CouponResponse;
import cus.study.spring.coupon.exception.CouponLackException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    public List<CouponResponse> findAllCoupon() {
        return couponRepository.findAll()
                .stream()
                .map(CouponResponse::of)
                .collect(Collectors.toList());
    }

    public CouponResponse findOneCoupon(Long couponId) {
        return CouponResponse.of(couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new));
    }

    public CouponResponse createCoupon(CouponRequest request) {
        final Coupon createdCoupon = couponRepository.save(request.toCoupon());
        return CouponResponse.of(createdCoupon);
    }

    public void deductCoupon(Long couponId) {
        final Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(IllegalArgumentException::new);

        final int updatedCount = couponRepository.deductCoupon(coupon.getId());

        if (updatedCount == 0) {
            new CouponLackException("재고가 모두 소진되었습니다.");
        }

        sleep(2000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
