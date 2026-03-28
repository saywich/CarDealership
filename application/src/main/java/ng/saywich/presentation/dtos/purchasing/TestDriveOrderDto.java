package ng.saywich.presentation.dtos.purchasing;

import ng.saywich.presentation.dtos.cars.CarDto;

import java.util.Date;
import java.util.UUID;

public record TestDriveOrderDto(
    UUID orderId,
    CarDto requestedCar,
    CustomerDto customer,
    Date startDate
) { }
