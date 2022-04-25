package cus.study.spring.sms.event;

import cus.study.spring.sms.domain.Message;

public class KaKaOMessageEvent implements Message {

    public final boolean senderYn;
    private final String message;

    public KaKaOMessageEvent(String message, boolean senderYn) {
        this.message = message;
        this.senderYn = senderYn;
    }

    @Override
    public String senderTemplate() {
        return "[카카오 메세지 처리] ";
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
