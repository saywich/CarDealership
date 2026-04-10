package ng.saywich.application.contracts.order.operations;

import ng.saywich.application.contracts.dtos.cars.CarModelDto;
import ng.saywich.application.contracts.dtos.purchasing.CustomerDto;
import ng.saywich.application.contracts.dtos.purchasing.ManufactureOrderDto;

public final class CreateManufactureOrder {
    private CreateManufactureOrder() {}

    public record Request(
        CustomerDto customer,
        CarModelDto carModel
    ) {}

    public record Response(
        ManufactureOrderDto order
    ) {}
}