package ng.saywich.orderservice.application.services.order.listeners;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.orderservice.application.abstractions.order.IManufactureOrderRepository;
import ng.saywich.orderservice.application.messaging.ProcessedMessage;
import ng.saywich.shared.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.shared.events.ManufactureOrderResponseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ManufactureOrderListener {

    private final IManufactureOrderRepository manufactureOrderRepository;

    private final IProcessedMessageRepository processedMessageRepository;

    @KafkaListener(
        topics = "${kafka.topics.manufacture-order-response}",
        groupId = "order-service"
    )
    @Transactional
    public void handle(ManufactureOrderResponseEvent event) {
        String consumer = "manufacture-order-response-listener";

        if (processedMessageRepository.exists(event.eventId(), consumer)) {
            return;
        }

        List<CarManufactureOrder> orders =
            manufactureOrderRepository.query(new UUID[] { event.orderId() });

        if (orders.isEmpty()) {
            return;
        }

        CarManufactureOrder order = orders.get(0);

        if (event.isSuccess()) {
            order.waitForDelivery();
        } else {
            order.cancel();
        }

        manufactureOrderRepository.updateOrder(order);

        processedMessageRepository.create(
            new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
        );
    }
}
