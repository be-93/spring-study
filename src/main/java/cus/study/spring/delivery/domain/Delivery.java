package cus.study.spring.delivery.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "delivery_id")
    private Long id;

    private Long orderId;
    private String address;

    public Delivery(Long orderId, String address) {
        this.orderId = orderId;
        this.address = address;
    }
}
