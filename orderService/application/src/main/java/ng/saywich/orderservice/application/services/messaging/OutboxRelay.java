package ng.saywich.orderservice.application.services.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IOutboxMessageRepository;
import ng.saywich.orderservice.application.messaging.OutboxMessage;
import ng.saywich.shared.events.InStockOrderRequestEvent;
import ng.saywich.shared.events.InStockOrderResponseEvent;
import ng.saywich.shared.events.ManufactureOrderRequestEvent;
import ng.saywich.shared.events.ManufactureOrderResponseEvent;
import ng.saywich.shared.events.TestDriveOrderRequestEvent;
import ng.saywich.shared.events.TestDriveOrderResponseEvent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(value = "outbox.relay.enabled", havingValue = "true", matchIfMissing = true)
public class OutboxRelay {
    private final IOutboxMessageRepository outboxMessageRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Scheduled(fixedDelayString = "${outbox.relay.delay-ms:2000}")
    @Transactional
    public void publishPending() {
        List<OutboxMessage> messages = outboxMessageRepository.lockUnprocessedBatch(50);

        for (OutboxMessage message : messages) {
            try {
                Object event = deserialize(message);

                kafkaTemplate.send(message.topic(), message.messageKey(), event).get();
                outboxMessageRepository.markPublished(message.id());
            } catch (Exception e) {
                outboxMessageRepository.markFailed(message.id());
            }
        }
    }

    private Object deserialize(OutboxMessage message) throws Exception {
        return switch (message.topic()) {
            case "in-stock-order-request" ->
                objectMapper.readValue(message.payload(), InStockOrderRequestEvent.class);
            case "in-stock-order-response" ->
                objectMapper.readValue(message.payload(), InStockOrderResponseEvent.class);
            case "manufacture-order-request" ->
                objectMapper.readValue(message.payload(), ManufactureOrderRequestEvent.class);
            case "manufacture-order-response" ->
                objectMapper.readValue(message.payload(), ManufactureOrderResponseEvent.class);
            case "test-drive-order-request" ->
                objectMapper.readValue(message.payload(), TestDriveOrderRequestEvent.class);
            case "test-drive-order-response" ->
                objectMapper.readValue(message.payload(), TestDriveOrderResponseEvent.class);
            default -> throw new IllegalStateException("Unexpected topic: " + message.topic());
        };
    }
}
