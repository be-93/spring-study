package cus.study.spring.order.dto;

import cus.study.spring.order.domain.Order;
import lombok.Data;

@Data
public class OrderRequest {
    private Long deliveryId;

    private OrderRequest(Long deliveryId) {
        this.deliveryId = deliveryId;
    }

    public static OrderRequest of(Order order) {
        return new OrderRequest(order.getDeliveryId());
    }
}
