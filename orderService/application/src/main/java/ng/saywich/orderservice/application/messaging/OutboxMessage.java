package ng.saywich.orderservice.application.messaging;

import java.util.Date;
import java.util.UUID;

public record OutboxMessage(
    UUID id,
    String topic,
    String messageKey,
    EventType eventType,
    String payload,
    Date createdAt,
    Date publishedAt,
    Integer retryCount
) { }
