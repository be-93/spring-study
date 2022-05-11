package cus.study.spring.sms.event;

import cus.study.spring.order.event.OrderCreateEvent;
import cus.study.spring.sms.domain.Message;

public class KaKaOMessageEvent implements Message {

    public final boolean senderYn;
    private final String message;

    public KaKaOMessageEvent(String message, boolean senderYn) {
        this.message = message;
        this.senderYn = senderYn;
    }

    public KaKaOMessageEvent(OrderCreateEvent orderCreateEvent) {
        this.message = String.format("%d 번의 주문이 접수되었습니다.", orderCreateEvent.getOrder().getId());
        this.senderYn = true;
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
