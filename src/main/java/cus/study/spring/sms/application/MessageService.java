package cus.study.spring.sms.application;

import cus.study.spring.sms.domain.Message;
import cus.study.spring.sms.exception.SMSMessageException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class MessageService {
    private static int count = 0;
    private final Random random = new Random();

    @Retryable(
            value = SMSMessageException.class,
            maxAttempts = 2,
            backoff = @Backoff(delay = 2000)
    )
    public void sender(Message message) {
        if (count < 2) {
            count++;
            throw new SMSMessageException();
        }
        log.info(message.senderTemplate() + message.getMessage());
        count = 0;
    }
}
