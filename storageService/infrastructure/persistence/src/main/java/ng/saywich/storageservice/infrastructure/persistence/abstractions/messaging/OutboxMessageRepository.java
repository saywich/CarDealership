package ng.saywich.storageservice.infrastructure.persistence.abstractions.messaging;

import lombok.RequiredArgsConstructor;
import ng.saywich.storageservice.application.abstractions.messaging.IOutboxMessageRepository;
import ng.saywich.storageservice.application.models.messaging.OutboxMessage;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.messaging.IOutboxMessageDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.messaging.OutboxMessageMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.messaging.OutboxMessageDao;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OutboxMessageRepository implements IOutboxMessageRepository {
    private final IOutboxMessageDaoRepository repository;

    private final OutboxMessageMapper mapper;

    @Override
    public void create(OutboxMessage message) {
        repository.save(mapper.toOutboxMessageDao(message));
    }

    @Override
    public List<OutboxMessage> lockUnprocessedBatch(int batchSize) {
        return repository.lockUnprocessedBatch(batchSize).stream()
            .map(mapper::toOutboxMessage)
            .toList();
    }

    @Override
    public void markPublished(UUID messageId) {
        Optional<OutboxMessageDao> daoOpt = repository.findById(messageId);

        if (daoOpt.isEmpty()) {
            return;
        }

        repository.save(daoOpt.get().toBuilder()
            .publishedAt(Date.from(Instant.now()))
            .build());
    }

    @Override
    public void markFailed(UUID messageId) {
        Optional<OutboxMessageDao> daoOpt = repository.findById(messageId);

        if (daoOpt.isEmpty()) {
            return;
        }

        OutboxMessageDao dao = daoOpt.get();

        repository.save(dao.toBuilder()
            .retryCount(dao.getRetryCount() + 1)
            .build());
    }
}
