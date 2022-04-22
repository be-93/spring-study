package cus.study.spring.delivery.eventhandler;

import cus.study.spring.delivery.application.DeliveryService;
import cus.study.spring.delivery.event.DeliveryEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryEventHandler {
    private final DeliveryService deliveryService;

    @Async
    @EventListener
    public void createDeliveryEventPublish(DeliveryEvent deliveryEvent) {
        deliveryService.createDelivery(deliveryEvent);
    }
}
