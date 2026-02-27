package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.order.TestDriveOrder;

import java.util.Date;

public final class CreateTestDriveOrder {
    private CreateTestDriveOrder() {}

    public record Request(
        Car car,
        Customer customer,
        Date date
    ) {}

    public record Response(
        TestDriveOrder order
    ) {}
}