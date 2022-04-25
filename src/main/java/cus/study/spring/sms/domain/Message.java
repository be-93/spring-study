package cus.study.spring.sms.domain;

public interface Message {
    String senderTemplate();

    String getMessage();

    boolean getSenderYn();
}
