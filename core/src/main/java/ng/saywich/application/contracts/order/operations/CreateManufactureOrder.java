package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.presentation.dtos.cars.CarModelDto;
import ng.saywich.presentation.dtos.purchasing.CustomerDto;
import ng.saywich.presentation.dtos.purchasing.ManufactureOrderDto;

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