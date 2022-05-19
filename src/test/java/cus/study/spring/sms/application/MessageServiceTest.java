package cus.study.spring.sms.application;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MessageServiceTest {

    @MockBean
    MessageService messageService;

    @Test
    public void messageSendRetryTest() {
        // when
        final boolean actual = messageService.retrySender();

        // then
        verify(messageService, times(2)).retrySender();
    }
}