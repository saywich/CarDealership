package ng.saywich.presentation.dtos.cars;

import ng.saywich.domain.car.CarModel;

import java.util.UUID;

public record CarDto(
    UUID carId,
    String vinNumber,
    CarModelDto model
) {}
