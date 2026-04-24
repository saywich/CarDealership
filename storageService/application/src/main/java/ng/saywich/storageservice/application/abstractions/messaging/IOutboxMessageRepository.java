package ng.saywich.storageservice.application.abstractions.messaging;

import ng.saywich.storageservice.application.models.messaging.OutboxMessage;

import java.util.List;
import java.util.UUID;

public interface IOutboxMessageRepository {
    void create(OutboxMessage message);

    List<OutboxMessage> lockUnprocessedBatch(int batchSize);

    void markPublished(UUID messageId);

    void markFailed(UUID messageId);
}
