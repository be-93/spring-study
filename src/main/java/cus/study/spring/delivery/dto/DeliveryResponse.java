package cus.study.spring.delivery.dto;

import cus.study.spring.delivery.domain.Delivery;
import lombok.Data;

@Data
public class DeliveryResponse {

    private Long id;
    private Long orderId;
    private String address;

    private DeliveryResponse(Long id, Long orderId, String address) {
        this.id = id;
        this.orderId = orderId;
        this.address = address;
    }

    public static DeliveryResponse of(Delivery delivery) {
        return new DeliveryResponse(delivery.getId(), delivery.getId(), delivery.getAddress());
    }
}
