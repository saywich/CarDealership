package ng.saywich.storageservice.application.contracts.dtos.parts;

import ng.saywich.shared.domain.car.enums.FuelType;

import java.util.UUID;

public record EngineDto(
    UUID partId,
    String name,
    int power,
    double volume,
    FuelType fuelType
) { }
