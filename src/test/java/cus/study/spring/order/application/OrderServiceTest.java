package cus.study.spring.order.application;

import cus.study.spring.delivery.eventhandler.DeliveryEventHandler;
import cus.study.spring.sms.eventhandler.MessageEventHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private DeliveryEventHandler deliveryEventHandler;
    @MockBean
    private MessageEventHandler messageEventHandler;

    @Test
    public void eventPublisherTest() {
        orderService.createOrder();

        verify(deliveryEventHandler, times(1))
                .createDeliveryEventPublish(any());
        verify(messageEventHandler, times(1))
                .messageSenderEventPublish(any());
    }
}