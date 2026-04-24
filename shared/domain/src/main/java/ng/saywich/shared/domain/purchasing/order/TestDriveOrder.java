package ng.saywich.shared.domain.purchasing.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ng.saywich.shared.domain.car.Car;
import ng.saywich.shared.domain.purchasing.Customer;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
public class TestDriveOrder {
    @Getter
    UUID orderId;

    @Getter
    UUID requestedCarId;

    @Getter
    Customer customer;

    @Getter
    Date startDate;
}
