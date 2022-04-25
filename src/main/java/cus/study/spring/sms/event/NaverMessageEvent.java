package cus.study.spring.sms.event;

import cus.study.spring.sms.domain.Message;

public class NaverMessageEvent implements Message {

    private final String message;

    public NaverMessageEvent(String message) {
        this.message = message;
    }

    @Override
    public String senderTemplate() {
        return "[네이버 메세지 처리] ";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
