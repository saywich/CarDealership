package ng.saywich.infrastructure.persistence.models.cars;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.infrastructure.persistence.models.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "cars", schema = "cars")
public class CarDao extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "model_id")
    private CarModelDao carModel;

    @OneToOne
    @JoinColumn(name = "option_set_id")
    private OptionSetDao optionSet;

    @Column(name = "vin_number")
    String vinNumber;
}
