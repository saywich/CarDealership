package ng.saywich.orderservice.application.services.order.publishers;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.messaging.EventType;
import ng.saywich.orderservice.application.services.messaging.OutboxService;
import ng.saywich.shared.events.ManufactureOrderRequestEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ManufactureOrderPublisher {
    private final OutboxService outboxService;

    @Value("${kafka.topics.manufacture-order-request}")
    public String topicName;

    public void publish(ManufactureOrderRequestEvent event) {
        outboxService.add(
            topicName,
            event.orderId().toString(),
            EventType.MANUFACTURE_ORDER,
            event
        );
    }
}