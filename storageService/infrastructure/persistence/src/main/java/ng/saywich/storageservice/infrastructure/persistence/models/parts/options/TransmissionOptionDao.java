package ng.saywich.storageservice.infrastructure.persistence.models.parts.options;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.TransmissionDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.IPartOptionDao;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "available_transmissions", schema = "cars")
public class TransmissionOptionDao extends BaseEntity
    implements IPartOptionDao<TransmissionDao>
{
    @Column(name = "model_id")
    private UUID modelId;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    private TransmissionDao transmission;

    @Column(name = "price_delta")
    private BigDecimal priceDelta;

    @Override
    public TransmissionDao getPart() {
        return transmission;
    }

    @Override
    public BigDecimal getPriceDifference() {
        return priceDelta;
    }
}
