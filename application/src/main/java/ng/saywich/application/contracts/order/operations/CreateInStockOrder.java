package ng.saywich.application.contracts.order.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.CarInStockOrder;
import ng.saywich.domain.purchasing.order.states.OrderState;

public final class CreateInStockOrder {
    private CreateInStockOrder() {}

    public record Request(
        Manager manager,
        Customer customer,
        Car car
    ) {}

    public record Response(
        CarInStockOrder order
    ) {}
}
