package ng.saywich.presentation.dtos.purchasing;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.purchasing.order.states.OrderState;
import ng.saywich.presentation.dtos.cars.CarDto;
import ng.saywich.presentation.dtos.cars.CarModelDto;

import java.util.UUID;

public record ManufactureOrderDto(
    UUID orderId,
    OrderState orderState,
    ManagerDto manager,
    CustomerDto customer,
    CarModelDto carModel
) { }
