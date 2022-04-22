package cus.study.spring.sms.application;

import cus.study.spring.sms.domain.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class MessageService {
    private final Random random = new Random();

    public void sender(Message message) {
        log.info(message.senderTemplate() + message.getMessage());
    }
}
