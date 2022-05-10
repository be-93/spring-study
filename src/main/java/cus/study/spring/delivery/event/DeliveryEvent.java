package cus.study.spring.delivery.event;

import cus.study.spring.delivery.domain.Delivery;
import cus.study.spring.order.event.OrderCreateEvent;
import lombok.Data;

@Data
public class DeliveryEvent {
    private Long orderId;
    private String address;

    public DeliveryEvent(Long orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }

    public DeliveryEvent(OrderCreateEvent orderCreateEvent) {
        this.orderId = orderCreateEvent.getOrder().getId();
        this.address = String.format("%d 번 주소 1000 - 400 번지", orderCreateEvent.getOrder().getId());
    }

    public Delivery toDelivery() {
        return new Delivery(orderId, address);
    }
}
