package ng.saywich.orderservice.infrastructure.persistence.models.messaging;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@IdClass(ProcessedMessageDaoId.class)
@SuperBuilder(toBuilder = true)
@Table(name = "processed_messages", schema = "messaging")
public class ProcessedMessageDao {
    @Id
    @Column(name = "event_id")
    private UUID eventId;

    @Id
    @Column(name = "consumer")
    private String consumer;

    @Column(name = "processed_at")
    private Date processedAt;
}
