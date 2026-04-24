package ng.saywich.orderservice.application.contracts.order.operations;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.TestDriveOrderDto;

import java.util.Date;
import java.util.UUID;

public final class CreateTestDriveOrder {
    private CreateTestDriveOrder() {}

    public record Request(
        UUID carId,
        UUID customerId,
        Date date
    ) {}

    public record Response(
        TestDriveOrderDto order
    ) {}
}