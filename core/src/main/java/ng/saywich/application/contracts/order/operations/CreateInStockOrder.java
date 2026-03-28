package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.presentation.dtos.cars.CarDto;
import ng.saywich.presentation.dtos.purchasing.CustomerDto;
import ng.saywich.presentation.dtos.purchasing.InStockOrderDto;

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
