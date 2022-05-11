package cus.study.spring.sms.eventhandler;

import cus.study.spring.order.event.OrderCreateEvent;
import cus.study.spring.sms.application.MessageService;
import cus.study.spring.sms.domain.Message;
import cus.study.spring.sms.event.KaKaOMessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageEventHandler {

    private final MessageService messageService;

    @EventListener(condition = "!(#message.getSenderYn())")
    public void messageSenderEventPublish(final Message message) {
        messageService.sender(message);
    }

    @EventListener
    public void messageSenderEventPublish(final OrderCreateEvent orderCreateEvent) {
        messageService.sender(new KaKaOMessageEvent(orderCreateEvent));
    }
}
