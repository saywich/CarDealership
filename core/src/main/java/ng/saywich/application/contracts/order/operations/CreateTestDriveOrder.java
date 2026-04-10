package ng.saywich.application.contracts.order.operations;

import ng.saywich.application.contracts.dtos.cars.CarDto;
import ng.saywich.application.contracts.dtos.purchasing.CustomerDto;
import ng.saywich.application.contracts.dtos.purchasing.TestDriveOrderDto;

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