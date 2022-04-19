package cus.study.spring.coupon;

import cus.study.spring.coupon.application.CouponService;
import cus.study.spring.coupon.dto.CouponRequest;
import cus.study.spring.coupon.dto.CouponResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CouponTrafficTest {

    private final int 쿠폰_수량 = 100;
    private final BigDecimal 할인_가격 = BigDecimal.valueOf(3000);

    @Autowired
    CouponService couponService;

    @Test
    public void deductCoupon() throws InterruptedException {
        final CouponRequest couponRequest = CouponRequest.of(쿠폰_수량, 할인_가격);
        final CouponResponse createdCoupon = couponService.createCoupon(couponRequest);

        final int numberOfThread = 쿠폰_수량;
        final ExecutorService executor = Executors.newFixedThreadPool(numberOfThread);
        final CountDownLatch latch = new CountDownLatch(numberOfThread);

        IntStream.range(0, numberOfThread)
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
