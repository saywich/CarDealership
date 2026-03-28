package ng.saywich.infrastructure.persistence.models.purchasing;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ng.saywich.infrastructure.persistence.models.BaseEntity;
import ng.saywich.infrastructure.persistence.models.cars.CarDao;

import java.util.Date;

@Getter
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "test_drive_orders", schema = "purchasing")
public class TestDriveOrderDao extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "customer_id")
    private CustomerDao customer;

    @OneToOne
    @JoinColumn(name = "car_id")
    private CarDao car;

    @Column(name = "start_date")
    private Date startDate;
}
