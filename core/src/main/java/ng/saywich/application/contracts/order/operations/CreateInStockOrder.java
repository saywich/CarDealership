package ng.saywich.application.contracts.order.operations;

import ng.saywich.application.contracts.dtos.cars.CarDto;
import ng.saywich.application.contracts.dtos.purchasing.CustomerDto;
import ng.saywich.application.contracts.dtos.purchasing.InStockOrderDto;

public final class CreateInStockOrder {
    private CreateInStockOrder() {}

    public record Request(
        CustomerDto customer,
        CarDto car
    ) {}

    public record Response(
        InStockOrderDto order
    ) {}
}
