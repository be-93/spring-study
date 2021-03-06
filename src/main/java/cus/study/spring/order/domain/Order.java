package cus.study.spring.order.domain;

import cus.study.spring.common.domain.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    private Long deliveryId;

    public void updateDelivery(Long deliveryId) {
        this.deliveryId = deliveryId;
    }
}
