package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.CarManufactureOrder;
import ng.saywich.domain.purchasing.order.states.OrderState;

public final class CreateManufactureOrder {
    private CreateManufactureOrder() {}

    public record Request(
        OrderState state,
        Manager manager,
        Customer customer,
        CarModel carModel
    ) {}

    public record Response(
        CarManufactureOrder order
    ) {}
}