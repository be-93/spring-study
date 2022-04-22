package cus.study.spring.order.dto;

import cus.study.spring.order.domain.Order;
import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private Long deliveryId;

    private OrderResponse(Long id, Long deliveryId) {
        this.id = id;
        this.deliveryId = deliveryId;
    }

    public static OrderResponse of(Order order) {
        return new OrderResponse(order.getId(), order.getDeliveryId());
    }
}
