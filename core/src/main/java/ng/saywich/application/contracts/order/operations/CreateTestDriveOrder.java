package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.order.TestDriveOrder;
import ng.saywich.presentation.dtos.cars.CarDto;
import ng.saywich.presentation.dtos.purchasing.CustomerDto;
import ng.saywich.presentation.dtos.purchasing.TestDriveOrderDto;

import java.util.Date;

public final class CreateTestDriveOrder {
    private CreateTestDriveOrder() {}

    public record Request(
        CarDto car,
        CustomerDto customer,
        Date date
    ) {}

    public record Response(
        TestDriveOrderDto order
    ) {}
}