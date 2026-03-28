package ng.saywich.infrastructure.persistence.models.purchasing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import ng.saywich.infrastructure.persistence.models.cars.CarDao;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "in_stock_orders", schema = "purchasing")
public class InStockOrderDao extends BaseEntity {
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
    @JoinColumn(name = "car_id")
    private CarDao car;
}
