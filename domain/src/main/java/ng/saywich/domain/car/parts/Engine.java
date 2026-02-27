package ng.saywich.domain.car.parts;

import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Volume;

import java.util.UUID;

public record Engine(
    UUID partId,
    String name,
    HorsePower power,
    Volume volume,
    FuelType fuelType
) implements ICarPart {
    @Override
    public UUID getPartId() {
        return partId;
    }
}
