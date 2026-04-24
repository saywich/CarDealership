package ng.saywich.orderservice.infrastructure.persistence.models.messaging;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProcessedMessageDaoId implements Serializable {
    private UUID eventId;

    private String consumer;
}
