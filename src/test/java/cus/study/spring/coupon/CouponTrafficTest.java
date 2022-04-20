package cus.study.spring.coupon;

import cus.study.spring.coupon.application.CouponService;
import cus.study.spring.coupon.dto.CouponRequest;
import cus.study.spring.coupon.dto.CouponResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class CouponTrafficTest {

    private final int 쿠폰_수량 = 100;
    private final BigDecimal 할인_가격 = BigDecimal.valueOf(3000);

    @Autowired
    CouponService couponService;

    @Test
    public void concurrencyDeductCoupon() throws InterruptedException {
        final CouponRequest couponRequest = CouponRequest.of(쿠폰_수량, 할인_가격);
        final CouponResponse createdCoupon = couponService.createCoupon(couponRequest);

        final int tryCount = 쿠폰_수량 + 10;
        final ExecutorService executor = Executors.newFixedThreadPool(10);
        final CountDownLatch latch = new CountDownLatch(tryCount);

        IntStream.range(0, tryCount)
                .forEach(i -> {
                    executor.execute(() -> {
                        couponService.deductCoupon(createdCoupon.getId());
                        latch.countDown();
                    });
                });

        latch.await();

        final CouponResponse findCoupon = couponService.findOneCoupon(createdCoupon.getId());

        assertThat(findCoupon.getQuantity()).isEqualTo(0);
    }

}
