package ng.saywich.orderservice.application.services.order.listeners;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.orderservice.application.abstractions.order.IInStockOrderRepository;
import ng.saywich.orderservice.application.messaging.ProcessedMessage;
import ng.saywich.shared.domain.purchasing.order.CarInStockOrder;
import ng.saywich.shared.events.InStockOrderResponseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class InStockOrderListener {
    private final IInStockOrderRepository inStockOrderRepository;

    private final IProcessedMessageRepository processedMessageRepository;

    @KafkaListener(
        topics = "${kafka.topics.in-stock-order-response}",
        groupId = "order-service"
    )
    @Transactional
    public void handle(InStockOrderResponseEvent event) {
        String consumer = "in-stock-order-response-listener";

        if (processedMessageRepository.exists(event.eventId(), consumer)) {
            return;
        }

        List<CarInStockOrder> orders =
            inStockOrderRepository.query(new UUID[] { event.orderId() });

        if (orders.isEmpty()) {
            return;
        }

        CarInStockOrder order = orders.get(0);

        if (event.isSuccess()) {
            order.ready();
        } else {
            order.cancel();
        }

        inStockOrderRepository.updateOrder(order);

        processedMessageRepository.create(
            new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
        );
    }
}
