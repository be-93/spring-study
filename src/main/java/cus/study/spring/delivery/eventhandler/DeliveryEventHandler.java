package cus.study.spring.delivery.eventhandler;

import cus.study.spring.delivery.application.DeliveryService;
import cus.study.spring.delivery.event.DeliveryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeliveryEventHandler {
    private final DeliveryService deliveryService;

    @EventListener
    public void createDelivery(DeliveryEvent deliveryEvent) {
        deliveryService.createDelivery(deliveryEvent);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(DeliveryEvent deliveryEvent) {
        log.info("BEFORE_COMMIT");
//        throw new RuntimeException("Server shutdown");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(DeliveryEvent deliveryEvent) {
        log.info("AFTER_COMMIT");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(DeliveryEvent deliveryEvent) {
        log.info("AFTER_ROLLBACK");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void completion(DeliveryEvent deliveryEvent) {
        log.info("AFTER_COMPLETION");
//        throw new RuntimeException("Server shutdown");
    }
}
