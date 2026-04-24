package ng.saywich.storageservice.infrastructure.persistence.models.parts.options;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.InteriorDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.IPartOptionDao;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "available_interiors", schema = "cars")
public class InteriorOptionDao extends BaseEntity implements IPartOptionDao<InteriorDao> {
    @Column(name = "model_id")
    private UUID modelId;

    @ManyToOne
    @JoinColumn(name = "interior_id")
    private InteriorDao interior;

    @Column(name = "price_delta")
    private BigDecimal priceDelta;

    @Override
    public InteriorDao getPart() {
        return interior;
    }

    @Override
    public BigDecimal getPriceDifference() {
        return priceDelta;
    }
}
