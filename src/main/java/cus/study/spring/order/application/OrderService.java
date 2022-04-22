package cus.study.spring.order.application;

import cus.study.spring.delivery.event.DeliveryEvent;
import cus.study.spring.delivery.eventhandler.DeliveryEventHandler;
import cus.study.spring.order.domain.Order;
import cus.study.spring.order.domain.OrderRepository;
import cus.study.spring.sms.event.KaKaOMessageEvent;
import cus.study.spring.sms.eventhandler.MessageEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final MessageEventHandler messageEventHandler;
    private final DeliveryEventHandler deliveryEventHandler;

    public void createOrder() {
        final Order savedOrder = orderRepository.save(new Order());

        final String message = String.format("%d 번의 주문이 접수되었습니다.", savedOrder.getId());
        final String address = String.format("%d 번 주소 1000 - 400 번지", savedOrder.getId());
        messageEventHandler.messageSenderEventPublish(new KaKaOMessageEvent(message));
        deliveryEventHandler.createDeliveryEventPublish(new DeliveryEvent(savedOrder.getId(), address));
    }
}
