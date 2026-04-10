package ng.saywich.application.contracts.dtos.purchasing;

import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.application.contracts.dtos.cars.CarModelDto;

import java.util.UUID;

public record ManufactureOrderDto(
    UUID orderId,
    OrderState orderState,
    ManagerDto manager,
    CustomerDto customer,
    CarModelDto carModel
) { }
