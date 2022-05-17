package cus.study.spring.sms.exception;

public class SMSMessageException extends RuntimeException {
    public SMSMessageException() {
        super();
    }

    public SMSMessageException(String message) {
        super(message);
    }
}
