package ng.saywich.orderservice.application.services.order.publishers;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.messaging.EventType;
import ng.saywich.orderservice.application.services.messaging.OutboxService;
import ng.saywich.shared.events.TestDriveOrderRequestEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDriveOrderPublisher {
    private final OutboxService outboxService;

    @Value("${kafka.topics.test-drive-order-request}")
    private String topicName;

    public void publish(TestDriveOrderRequestEvent event) {
        outboxService.add(
            topicName,
            event.orderId().toString(),
            EventType.TEST_DRIVE_ORDER,
            event
        );
    }
}
