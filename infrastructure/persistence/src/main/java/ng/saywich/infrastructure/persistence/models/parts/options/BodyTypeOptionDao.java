package ng.saywich.infrastructure.persistence.models.parts.options;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "available_body_types", schema = "cars")
public class BodyTypeOptionDao extends BaseEntity implements IPartOptionDao<BodyType> {
    @Column(name = "model_id")
    private UUID modelId;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_type")
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private BodyType bodyType;

    @Column(name = "price_delta")
    private BigDecimal priceDelta;

    @Override
    public BodyType getPart() {
        return bodyType;
    }

    @Override
    public BigDecimal getPriceDifference() {
        return priceDelta;
    }
}