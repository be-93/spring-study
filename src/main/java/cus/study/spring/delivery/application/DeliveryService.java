package cus.study.spring.delivery.application;

import cus.study.spring.delivery.domain.Delivery;
import cus.study.spring.delivery.domain.DeliveryRepository;
import cus.study.spring.delivery.event.DeliveryEvent;
import cus.study.spring.order.domain.Order;
import cus.study.spring.order.domain.OrderRepository;
import cus.study.spring.sms.event.NaverMessageEvent;
import cus.study.spring.sms.eventhandler.MessageEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final OrderRepository orderRepository;
    private final MessageEventHandler messageEventHandler;

    public void createDelivery(DeliveryEvent deliveryEvent) {
        Delivery savedDelivery = deliveryRepository.save(deliveryEvent.toDelivery());
        Order order = orderRepository.findById(deliveryEvent.getOrderId())
                .orElseThrow(IllegalAccessError::new);

        order.updateDelivery(savedDelivery.getId());

        final String message = String.format("%d 번 배달이 배정되었습니다.", savedDelivery.getId());

        messageEventHandler.messageSenderEventPublish(new NaverMessageEvent(message));
    }
}
