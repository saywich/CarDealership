package ng.saywich.storageservice.infrastructure.persistence.models.parts.options;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.RimsDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.IPartOptionDao;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "available_rims", schema = "cars")
public class RimsOptionDao extends BaseEntity implements IPartOptionDao<RimsDao> {
    @Column(name = "model_id")
    private UUID modelId;

    @ManyToOne
    @JoinColumn(name = "rims_id")
    private RimsDao rims;

    @Column(name = "price_delta")
    private BigDecimal priceDelta;

    @Override
    public RimsDao getPart() {
        return rims;
    }

    @Override
    public BigDecimal getPriceDifference() {
        return priceDelta;
    }
}
