package ng.saywich.storageservice.infrastructure.persistence.models.messaging;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.application.models.messaging.EventType;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "outbox_messages", schema = "messaging")
public class OutboxMessageDao {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "topic")
    private String topic;

    @Column(name = "message_key")
    private String messageKey;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "event_type")
    private EventType eventType;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "jsonb")
    private String payload;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "retry_count")
    private Integer retryCount;
}
