package ng.saywich.presentation.dtos.purchasing;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.purchasing.Customer;
import ng.saywich.domain.purchasing.Manager;
import ng.saywich.domain.purchasing.order.states.IOrderState;
import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.presentation.dtos.cars.CarDto;

import java.util.UUID;

public record InStockOrderDto(
    UUID orderId,
    OrderState orderState,
    ManagerDto manager,
    CustomerDto customer,
    CarDto car
) { }
