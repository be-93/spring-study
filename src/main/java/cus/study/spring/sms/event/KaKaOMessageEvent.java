package cus.study.spring.sms.event;

import cus.study.spring.sms.domain.Message;

public class KaKaOMessageEvent implements Message {

    private final String message;

    public KaKaOMessageEvent(String message) {
        this.message = message;
    }

    @Override
    public String senderTemplate() {
        return "\n [카카오 메세지 처리] \n";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
