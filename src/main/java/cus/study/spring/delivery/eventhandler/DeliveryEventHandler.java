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
    public void createDelivery(final DeliveryEvent deliveryEvent) {
        deliveryService.createDelivery(deliveryEvent);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(final DeliveryEvent deliveryEvent) {
        log.info("BEFORE_COMMIT");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(final DeliveryEvent deliveryEvent) {
        log.info("AFTER_COMMIT");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(final DeliveryEvent deliveryEvent) {
        log.info("AFTER_ROLLBACK");
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void completion(final DeliveryEvent deliveryEvent) {
        log.info("AFTER_COMPLETION");
    }
}
