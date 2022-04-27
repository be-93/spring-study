package cus.study.spring.order.ui;

import cus.study.spring.order.application.OrderService;
import cus.study.spring.order.domain.Order;
import cus.study.spring.order.dto.OrderRequest;
import cus.study.spring.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(
                orders
                        .stream()
                        .map(OrderResponse::of)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOneOrder(@PathVariable("orderId") Long orderId) {
        Order order = orderService.findOneOrder(orderId);
        return ResponseEntity.ok(OrderResponse.of(order));
    }

    @PostMapping
    public ResponseEntity<Void> createOrder() {
        orderService.createOrder();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateDeliveryId(@PathVariable("orderId") Long orderId,
                                                 @RequestBody OrderRequest orderRequest) {
        orderService.updateDeliverId(orderId, orderRequest);
        return ResponseEntity.noContent()
                .build();
    }
}
