package ng.saywich.orderservice.infrastructure.persistence.models.purchasing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.orderservice.infrastructure.persistence.models.BaseEntity;

import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "test_drive_orders", schema = "purchasing")
public class TestDriveOrderDao extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerDao customer;

    @Column(name = "car_id")
    private UUID carId;

    @Column(name = "start_date")
    private Date startDate;
}
