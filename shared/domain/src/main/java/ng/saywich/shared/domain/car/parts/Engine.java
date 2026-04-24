package ng.saywich.shared.domain.car.parts;

import ng.saywich.shared.domain.car.enums.FuelType;
import ng.saywich.shared.domain.value.HorsePower;
import ng.saywich.shared.domain.value.Volume;

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
