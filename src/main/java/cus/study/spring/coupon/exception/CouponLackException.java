package cus.study.spring.coupon.exception;

public class CouponLackException extends RuntimeException {
    public CouponLackException() {
        super();
    }

    public CouponLackException(String message) {
        super(message);
    }
}
