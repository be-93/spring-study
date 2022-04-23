package cus.study.spring.sms.eventhandler;

import cus.study.spring.sms.application.MessageService;
import cus.study.spring.sms.domain.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageEventHandler {

    private final MessageService messageService;

    @Order(1)
    @Async
    @EventListener
    public void messageSenderEventPublish(Message message) {
        log.info("Order(1)");
        messageService.sender(message);
    }

    @Order(2)
    @Async
    @EventListener
    public void messageSenderEventPublish2(Message message) {
        log.info("Order(2)");
        messageService.sender(message);
    }

}
