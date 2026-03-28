package ng.saywich.infrastructure.persistence.models.parts.options;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import ng.saywich.infrastructure.persistence.models.parts.EngineDao;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "available_engines", schema = "cars")
public class EngineOptionDao extends BaseEntity implements IPartOptionDao<EngineDao> {
    @Column(name = "model_id")
    private UUID modelId;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private EngineDao engine;

    @Column(name = "price_delta")
    private BigDecimal priceDelta;

    @Override
    public EngineDao getPart() {
        return engine;
    }

    @Override
    public BigDecimal getPriceDifference() {
        return priceDelta;
    }
}
