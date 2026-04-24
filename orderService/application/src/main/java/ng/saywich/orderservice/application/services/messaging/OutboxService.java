package ng.saywich.orderservice.application.services.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IOutboxMessageRepository;
import ng.saywich.orderservice.application.messaging.EventType;
import ng.saywich.orderservice.application.messaging.OutboxMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OutboxService {
    private final IOutboxMessageRepository outboxMessageRepository;
    private final ObjectMapper objectMapper;

    @Transactional
    public void add(String topic, String messageKey, EventType eventType, Object event) {
        try {
            outboxMessageRepository.create(new OutboxMessage(
                UUID.randomUUID(),
                topic,
                messageKey,
                eventType,
                objectMapper.writeValueAsString(event),
                Date.from(Instant.now()),
                null,
                0
            ));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
