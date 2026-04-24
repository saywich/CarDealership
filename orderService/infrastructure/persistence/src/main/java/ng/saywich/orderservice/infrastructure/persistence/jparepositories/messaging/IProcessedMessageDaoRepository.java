package ng.saywich.orderservice.infrastructure.persistence.jparepositories.messaging;

import ng.saywich.orderservice.infrastructure.persistence.models.messaging.ProcessedMessageDao;
import ng.saywich.orderservice.infrastructure.persistence.models.messaging.ProcessedMessageDaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IProcessedMessageDaoRepository
    extends JpaRepository<ProcessedMessageDao, ProcessedMessageDaoId> {
    boolean existsByEventIdAndConsumer(UUID eventId, String consumer);
}
