package ng.saywich.orderservice.application.messaging;

import java.util.Date;
import java.util.UUID;

public record ProcessedMessage(
    UUID eventId,
    String consumer,
    Date processedAt
) { }
