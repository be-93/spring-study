package cus.study.spring.sms.event;

import cus.study.spring.sms.domain.Message;

public class NaverMessageEvent implements Message {

    public final boolean senderYn;
    private final String message;

    public NaverMessageEvent(String message, boolean senderYn) {
        this.message = message;
        this.senderYn = senderYn;
    }

    @Override
    public String senderTemplate() {
        return "[네이버 메세지 처리] ";
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean getSenderYn() {
        return senderYn;
    }
}
