package ng.saywich.orderservice.infrastructure.persistence.abstractions.messaging;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IOutboxMessageRepository;
import ng.saywich.orderservice.application.messaging.OutboxMessage;
import ng.saywich.orderservice.infrastructure.persistence.jparepositories.messaging.IOutboxMessageDaoRepository;
import ng.saywich.orderservice.infrastructure.persistence.mappers.messaging.OutboxMessageMapper;
import ng.saywich.orderservice.infrastructure.persistence.models.messaging.OutboxMessageDao;
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
