package ng.saywich.storageservice.infrastructure.persistence.jparepositories.messaging;

import ng.saywich.storageservice.infrastructure.persistence.models.messaging.OutboxMessageDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IOutboxMessageDaoRepository extends JpaRepository<OutboxMessageDao, UUID> {
    @Query(
        value = """
            SELECT *
            FROM messaging.outbox_messages
            WHERE published_at IS NULL
            ORDER BY created_at
            LIMIT :batchSize
            FOR UPDATE SKIP LOCKED
        """,
        nativeQuery = true
    )
    List<OutboxMessageDao> lockUnprocessedBatch(@Param("batchSize") int batchSize);
}
