package ng.saywich.storageservice.infrastructure.persistence.models.stock;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.domain.stock.PartType;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "part_stock_units", schema = "stock")
public class PartStockUnitDao extends BaseEntity {
    @Column(name = "part_id")
    private UUID partId;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "part_type")
    private PartType partType;

    @Column(name = "total_count")
    private Integer totalCount;

    @Column(name = "reserved_count")
    private Integer reservedCount;
}