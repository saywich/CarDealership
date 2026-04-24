package ng.saywich.orderservice.infrastructure.persistence.models.purchasing;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.orderservice.infrastructure.persistence.models.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "customers", schema = "purchasing")
public class CustomerDao extends BaseEntity {
    @Column(name = "name")
    private String name;
}
