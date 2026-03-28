package ng.saywich.infrastructure.persistence.models.parts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.infrastructure.persistence.models.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "rims", schema = "parts")
public class RimsDao extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "diameter")
    private int diameter;
}
