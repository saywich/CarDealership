package ng.saywich.storageservice.application.abstractions.messaging;

import ng.saywich.storageservice.application.models.messaging.ProcessedMessage;

import java.util.UUID;

public interface IProcessedMessageRepository {
    boolean exists(UUID eventId, String consumer);

    void create(ProcessedMessage message);
}
