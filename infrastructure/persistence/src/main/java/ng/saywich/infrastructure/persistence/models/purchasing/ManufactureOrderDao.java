package ng.saywich.infrastructure.persistence.models.purchasing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import ng.saywich.infrastructure.persistence.models.cars.CarModelDao;
import ng.saywich.infrastructure.persistence.models.cars.OptionSetDao;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

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

    @OneToOne
    @JoinColumn(name = "model_id")
    private CarModelDao carModel;

    @OneToOne
    @JoinColumn(name = "option_set_id")
    private OptionSetDao optionSet;
}
