package ng.saywich.storageservice.infrastructure.persistence.models.parts.options;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.ColorDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.IPartOptionDao;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "available_colors", schema = "cars")
public class ColorOptionDao extends BaseEntity implements IPartOptionDao<ColorDao> {
    @Column(name = "model_id")
    private UUID modelId;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorDao color;

    @Column(name = "price_delta")
    private BigDecimal priceDelta;

    @Override
    public ColorDao getPart() {
        return color;
    }

    @Override
    public BigDecimal getPriceDifference() {
        return priceDelta;
    }
}
