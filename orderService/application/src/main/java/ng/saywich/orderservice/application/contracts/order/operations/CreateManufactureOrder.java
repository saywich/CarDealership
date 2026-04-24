package ng.saywich.orderservice.application.contracts.order.operations;

import ng.saywich.orderservice.application.contracts.dtos.purchasing.ManufactureOrderDto;

import java.util.UUID;

public final class CreateManufactureOrder {
    private CreateManufactureOrder() {}

    public record Request(
        UUID customerId,
        UUID carModelId,
        UUID optionSetId
    ) {}

    public record Response(
        ManufactureOrderDto order
    ) {}
}