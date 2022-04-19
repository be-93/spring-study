package cus.study.spring.coupon.application;

import cus.study.spring.coupon.domain.Coupon;
import cus.study.spring.coupon.domain.CouponHistory;
import cus.study.spring.coupon.domain.CouponHistoryRepository;
import cus.study.spring.coupon.domain.CouponRepository;
import cus.study.spring.coupon.dto.CouponRequest;
import cus.study.spring.coupon.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;
    private final CouponHistoryRepository couponHistoryRepository;

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

        final int beforeQuantity = coupon.getQuantity();

        coupon.deduct();

        final int afterQuantity = coupon.getQuantity();

        couponHistoryRepository.save(new CouponHistory(coupon.getId(), beforeQuantity, afterQuantity));

        sleep(1000);
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
