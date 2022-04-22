package cus.study.spring.sms.eventhandler;

import cus.study.spring.sms.application.MessageService;
import cus.study.spring.sms.domain.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageEventHandler {

    private final MessageService messageService;

    @Async
    @EventListener
    public void messageSenderEventPublish(Message message) {
        messageService.sender(message);
    }

}
