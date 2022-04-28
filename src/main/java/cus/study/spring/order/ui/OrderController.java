package cus.study.spring.order.ui;

import cus.study.spring.order.application.OrderService;
import cus.study.spring.order.domain.Order;
import cus.study.spring.order.dto.OrderRequest;
import cus.study.spring.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        final List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(
                orders
                        .stream()
                        .map(OrderResponse::of)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> findOneOrder(@PathVariable("orderId") final Long orderId) {
        final Order order = orderService.findOneOrder(orderId);
        return ResponseEntity.ok(OrderResponse.of(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder() {
        final Order savedOrder = orderService.createOrder();
        final URI uri = URI.create("order/" + savedOrder.getId());

        return ResponseEntity.created(uri)
                .body(OrderResponse.of(savedOrder));
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponse> updateDeliveryId(@PathVariable("orderId") final Long orderId,
                                                 @RequestBody final OrderRequest orderRequest) {
        final Order order = orderService.updateDeliverId(orderId, orderRequest);
        return ResponseEntity.ok(OrderResponse.of(order));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("orderId") Long orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent()
                .build();
    }
}
