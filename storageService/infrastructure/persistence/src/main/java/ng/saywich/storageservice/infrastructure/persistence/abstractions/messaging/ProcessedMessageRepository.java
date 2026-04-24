package ng.saywich.storageservice.infrastructure.persistence.abstractions.messaging;

import lombok.RequiredArgsConstructor;
import ng.saywich.storageservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.storageservice.application.models.messaging.ProcessedMessage;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.messaging.IProcessedMessageDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.messaging.ProcessedMessageMapper;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProcessedMessageRepository implements IProcessedMessageRepository {
    private final IProcessedMessageDaoRepository repository;

    private final ProcessedMessageMapper mapper;

    @Override
    public boolean exists(UUID eventId, String consumer) {
        return repository.existsByEventIdAndConsumer(eventId, consumer);
    }

    @Override
    public void create(ProcessedMessage message) {
        repository.save(mapper.toProcessedMessageDao(message));
    }
}
