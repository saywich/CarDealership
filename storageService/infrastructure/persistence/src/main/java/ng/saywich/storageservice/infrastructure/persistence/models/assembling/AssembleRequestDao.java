package ng.saywich.storageservice.infrastructure.persistence.models.assembling;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.storageservice.domain.assembling.AssembleRequestStatus;
import ng.saywich.storageservice.infrastructure.persistence.models.BaseEntity;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "assemble_requests", schema = "assembling")
public class AssembleRequestDao extends BaseEntity {
    @Column(name = "order_id")
    private UUID orderId;

    @ManyToOne
    @JoinColumn(name = "warehouse_admin_id")
    private WarehouseAdminDao warehouseAdmin;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status")
    private AssembleRequestStatus status;
}