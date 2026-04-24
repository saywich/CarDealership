package ng.saywich.storageservice.infrastructure.persistence.models.parts;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "colors", schema = "parts")
public class ColorDao extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "hex_code")
    private int hexCode;
}
