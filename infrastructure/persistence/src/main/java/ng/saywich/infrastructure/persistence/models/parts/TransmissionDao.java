package ng.saywich.infrastructure.persistence.models.parts;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.TransmissionType;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "transmissions", schema = "parts")
public class TransmissionDao extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "gears_count")
    private int gearsCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private TransmissionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "drive_train")
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private DriveTrain driveTrain;
}
