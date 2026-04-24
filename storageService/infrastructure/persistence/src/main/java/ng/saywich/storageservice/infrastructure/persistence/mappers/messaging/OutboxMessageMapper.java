package ng.saywich.storageservice.infrastructure.persistence.mappers.messaging;

import ng.saywich.storageservice.application.models.messaging.OutboxMessage;
import ng.saywich.storageservice.infrastructure.persistence.models.messaging.OutboxMessageDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OutboxMessageMapper {
    OutboxMessageDao toOutboxMessageDao(OutboxMessage message);

    OutboxMessage toOutboxMessage(OutboxMessageDao dao);
}
