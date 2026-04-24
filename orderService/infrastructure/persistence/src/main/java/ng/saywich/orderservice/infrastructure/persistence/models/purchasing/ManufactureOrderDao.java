package ng.saywich.orderservice.infrastructure.persistence.models.purchasing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.shared.domain.purchasing.order.states.OrderState;
import ng.saywich.orderservice.infrastructure.persistence.models.BaseEntity;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "manufacture_orders", schema = "purchasing")
public class ManufactureOrderDao extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(name = "order_state")
    @JdbcType(value = PostgreSQLEnumJdbcType.class)
    private OrderState orderState;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private ManagerDao manager;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerDao customer;

    @Column(name = "model_id")
    private UUID carModelId;

    @Column(name = "option_set_id")
    private UUID optionSetId;
}
