package ng.saywich.application.contracts.dtos.parts;

import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Volume;

import java.util.UUID;

public record EngineDto(
    UUID partId,
    String name,
    int power,
    double volume,
    FuelType fuelType
) { }
