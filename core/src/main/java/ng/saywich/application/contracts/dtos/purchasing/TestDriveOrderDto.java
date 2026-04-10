package ng.saywich.application.contracts.dtos.purchasing;

import ng.saywich.application.contracts.dtos.cars.CarDto;

import java.util.Date;
import java.util.UUID;

public record TestDriveOrderDto(
    UUID orderId,
    CarDto requestedCar,
    CustomerDto customer,
    Date startDate
) { }
