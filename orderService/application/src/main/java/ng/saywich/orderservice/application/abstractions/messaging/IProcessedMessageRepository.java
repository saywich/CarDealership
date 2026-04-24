package ng.saywich.orderservice.application.abstractions.messaging;


import ng.saywich.orderservice.application.messaging.ProcessedMessage;

import java.util.UUID;

public interface IProcessedMessageRepository {
    boolean exists(UUID eventId, String consumer);

    void create(ProcessedMessage message);
}
