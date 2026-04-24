package ng.saywich.storageservice.infrastructure.persistence.models.cars;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.*;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "option_sets", schema = "cars")
public class OptionSetDao extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "available_engine_id")
    private EngineOptionDao engine;

    @ManyToOne
    @JoinColumn(name = "available_transmission_id")
    private TransmissionOptionDao transmission;

    @ManyToOne
    @JoinColumn(name = "available_rims_id")
    private RimsOptionDao rims;

    @ManyToOne
    @JoinColumn(name = "available_interior_id")
    private InteriorOptionDao interior;

    @ManyToOne
    @JoinColumn(name = "available_color_id")
    private ColorOptionDao color;

    @ManyToOne
    @JoinColumn(name = "available_body_type_id")
    private BodyTypeOptionDao bodyType;
}
