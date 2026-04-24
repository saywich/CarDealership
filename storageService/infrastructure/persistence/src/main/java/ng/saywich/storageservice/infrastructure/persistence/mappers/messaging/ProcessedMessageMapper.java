package ng.saywich.storageservice.infrastructure.persistence.mappers.messaging;

import ng.saywich.storageservice.application.models.messaging.ProcessedMessage;
import ng.saywich.storageservice.infrastructure.persistence.models.messaging.ProcessedMessageDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProcessedMessageMapper {
    ProcessedMessageDao toProcessedMessageDao(ProcessedMessage message);

    ProcessedMessage toProcessedMessage(ProcessedMessageDao dao);
}
