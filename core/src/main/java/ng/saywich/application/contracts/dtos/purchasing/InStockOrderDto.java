package ng.saywich.application.contracts.dtos.purchasing;

import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.application.contracts.dtos.cars.CarDto;

import java.util.UUID;

public record InStockOrderDto(
    UUID orderId,
    OrderState orderState,
    ManagerDto manager,
    CustomerDto customer,
    CarDto car
) { }
