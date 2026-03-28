package ng.saywich.infrastructure.persistence.models.parts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "engines", schema = "parts")
public class EngineDao extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "power")
    private int power;

    @Column(name = "volume")
    private double volume;

    @Enumerated(EnumType.STRING)
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    @Column(name = "fuel_type")
    private FuelType fuelType;
}
