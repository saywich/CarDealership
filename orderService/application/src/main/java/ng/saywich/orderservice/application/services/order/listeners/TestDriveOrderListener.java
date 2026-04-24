package ng.saywich.orderservice.application.services.order.listeners;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.orderservice.application.abstractions.order.ITestDriveOrderRepository;
import ng.saywich.orderservice.application.messaging.ProcessedMessage;
import ng.saywich.shared.domain.purchasing.order.TestDriveOrder;
import ng.saywich.shared.events.TestDriveOrderResponseEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TestDriveOrderListener {
    private final ITestDriveOrderRepository testDriveOrderRepository;

    private final IProcessedMessageRepository processedMessageRepository;

    @KafkaListener(
        topics = "${kafka.topics.test-drive-order-response}",
        groupId = "order-service"
    )
    @Transactional
    public void handle(TestDriveOrderResponseEvent event) {
        String consumer = "test-drive-order-response-listener";

        if (processedMessageRepository.exists(event.eventId(), consumer)) {
            return;
        }

        List<TestDriveOrder> orders =
            testDriveOrderRepository.query(new UUID[] { event.orderId() });

        if (orders.isEmpty()) {
            return;
        }

        processedMessageRepository.create(
            new ProcessedMessage(event.eventId(), consumer, Date.from(Instant.now()))
        );
    }
}
