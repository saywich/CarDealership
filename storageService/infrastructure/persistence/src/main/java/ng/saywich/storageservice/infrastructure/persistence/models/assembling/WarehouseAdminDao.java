package ng.saywich.storageservice.infrastructure.persistence.models.assembling;

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
@Table(name = "warehouse_admins", schema = "assembling")
public class WarehouseAdminDao extends BaseEntity {
    @Column(name = "name")
    private String name;
}