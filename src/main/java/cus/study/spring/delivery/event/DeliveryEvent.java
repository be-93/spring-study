package cus.study.spring.delivery.event;

import cus.study.spring.delivery.domain.Delivery;
import lombok.Data;

@Data
public class DeliveryEvent {
    private Long orderId;
    private String address;

    public DeliveryEvent(Long orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }

    public Delivery toDelivery() {
        return new Delivery(orderId, address);
    }
}
