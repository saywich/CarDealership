package ng.saywich.orderservice.infrastructure.persistence.mappers.messaging;

import ng.saywich.orderservice.application.messaging.OutboxMessage;
import ng.saywich.orderservice.infrastructure.persistence.models.messaging.OutboxMessageDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OutboxMessageMapper {
    OutboxMessageDao toOutboxMessageDao(OutboxMessage message);

    OutboxMessage toOutboxMessage(OutboxMessageDao dao);
}
