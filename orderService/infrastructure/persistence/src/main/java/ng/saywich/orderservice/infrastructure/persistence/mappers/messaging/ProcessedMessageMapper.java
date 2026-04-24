package ng.saywich.orderservice.infrastructure.persistence.mappers.messaging;

import ng.saywich.orderservice.application.messaging.ProcessedMessage;
import ng.saywich.orderservice.infrastructure.persistence.models.messaging.ProcessedMessageDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProcessedMessageMapper {
    ProcessedMessageDao toProcessedMessageDao(ProcessedMessage message);

    ProcessedMessage toProcessedMessage(ProcessedMessageDao dao);
}
