package ng.saywich.storageservice.infrastructure.persistence.jparepositories.messaging;

import ng.saywich.storageservice.infrastructure.persistence.models.messaging.ProcessedMessageDao;
import ng.saywich.storageservice.infrastructure.persistence.models.messaging.ProcessedMessageDaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProcessedMessageDaoRepository
    extends JpaRepository<ProcessedMessageDao, ProcessedMessageDaoId> {
    boolean existsByEventIdAndConsumer(UUID eventId, String consumer);
}
