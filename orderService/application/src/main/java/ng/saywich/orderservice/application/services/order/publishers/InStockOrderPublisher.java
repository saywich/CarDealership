package ng.saywich.orderservice.application.services.order.publishers;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.messaging.EventType;
import ng.saywich.orderservice.application.services.messaging.OutboxService;
import ng.saywich.shared.events.InStockOrderRequestEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InStockOrderPublisher {
    private final OutboxService outboxService;

    @Value("${kafka.topics.in-stock-order-request}")
    public String topicName;

    public void publish(InStockOrderRequestEvent event) {
        outboxService.add(
            topicName,
            event.carId().toString(),
            EventType.IN_STOCK_ORDER,
            event
        );
    }
}
