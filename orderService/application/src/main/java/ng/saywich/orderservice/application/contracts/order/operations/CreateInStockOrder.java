package ng.saywich.orderservice.application.contracts.order.operations;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.InStockOrderDto;

import java.util.UUID;

public final class CreateInStockOrder {
    private CreateInStockOrder() {}

    public record Request(
        UUID customerId,
        UUID carId
    ) {}

    public record Response(
        InStockOrderDto order
    ) {}
}
