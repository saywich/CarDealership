package ng.saywich.domain.purchasing.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ng.saywich.domain.car.Car;
import ng.saywich.domain.purchasing.Customer;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class TestDriveOrder {
    @Getter
    UUID orderId;

    @Getter
    Car requestedCar;

    @Getter
    Customer customer;

    @Getter
    Date startDate;
}
