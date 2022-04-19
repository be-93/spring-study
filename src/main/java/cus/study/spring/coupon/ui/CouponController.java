package cus.study.spring.coupon.ui;

import cus.study.spring.coupon.application.CouponService;
import cus.study.spring.coupon.dto.CouponRequest;
import cus.study.spring.coupon.dto.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("coupon")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<CouponResponse>> findAllCoupon() {
        return ResponseEntity.ok(couponService.findAllCoupon());
    }

    @GetMapping("/{couponId}")
    public ResponseEntity<CouponResponse> findOneCoupon(@PathVariable Long couponId) {
        return ResponseEntity.ok(couponService.findOneCoupon(couponId));
    }

    @PostMapping
    public ResponseEntity<CouponResponse> create(@RequestBody CouponRequest request) {
        final CouponResponse createdCoupon = couponService.createCoupon(request);
        final URI uri = URI.create("/coupon/" + createdCoupon.getId());
        return ResponseEntity.created(uri)
                .body(createdCoupon);
    }

    @PatchMapping("/deduct/{couponId}")
    public ResponseEntity<Void> deductCoupon(@PathVariable Long couponId) {
        couponService.deductCoupon(couponId);
        return ResponseEntity.ok()
                .build();
    }

}
