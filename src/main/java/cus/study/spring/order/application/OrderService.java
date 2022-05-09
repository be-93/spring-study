package cus.study.spring.order.application;

import cus.study.spring.order.domain.Order;
import cus.study.spring.order.domain.OrderRepository;
import cus.study.spring.order.dto.OrderRequest;
import cus.study.spring.order.event.OrderCreateEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Order createOrder() {
        final Order savedOrder = orderRepository.save(new Order());

        eventPublisher.publishEvent(new OrderCreateEvent(savedOrder));

        return savedOrder;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Cacheable(value = "order", key = "#orderId")
    @Transactional(readOnly = true)
    public Order findOneOrder(final Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow();
    }

    @CachePut(value = "order", key = "#orderId")
    public Order updateDeliverId(final Long orderId, final OrderRequest orderRequest) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow();

        order.updateDelivery(orderRequest.getDeliveryId());

        return order;
    }

    @CacheEvict(value = "order", key = "#orderId")
    public void deleteOrder(final Long orderId) {
        final Order order = orderRepository.findById(orderId)
                .orElseThrow();

        orderRepository.delete(order);
    }
}
