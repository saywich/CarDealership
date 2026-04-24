package ng.saywich.orderservice.infrastructure.persistence.abstractions.messaging;

import lombok.RequiredArgsConstructor;
import ng.saywich.orderservice.application.abstractions.messaging.IProcessedMessageRepository;
import ng.saywich.orderservice.application.messaging.ProcessedMessage;
import ng.saywich.orderservice.infrastructure.persistence.jparepositories.messaging.IProcessedMessageDaoRepository;
import ng.saywich.orderservice.infrastructure.persistence.mappers.messaging.ProcessedMessageMapper;
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
