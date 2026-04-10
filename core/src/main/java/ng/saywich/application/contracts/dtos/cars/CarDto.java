package ng.saywich.application.contracts.dtos.cars;

import java.util.UUID;

public record CarDto(
    UUID carId,
    String vinNumber,
    CarModelDto model
) {}
