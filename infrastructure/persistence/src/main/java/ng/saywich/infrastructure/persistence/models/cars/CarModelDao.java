package ng.saywich.infrastructure.persistence.models.cars;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.infrastructure.persistence.models.BaseEntity;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "models", schema = "cars")
public class CarModelDao extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "brand_id")
    private CarBrandDao brand;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "default_option_set_id")
    private OptionSetDao defaultOptionSet;

    @Column(name = "price")
    private BigDecimal price;
}