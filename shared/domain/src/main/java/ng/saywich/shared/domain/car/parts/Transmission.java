package ng.saywich.shared.domain.car.parts;

import ng.saywich.shared.domain.car.enums.DriveTrain;
import ng.saywich.shared.domain.car.enums.TransmissionType;

import java.util.UUID;

public record Transmission(
    UUID partId,
    String name,
    int gearsCount,
    TransmissionType type,
    DriveTrain driveTrain
) implements ICarPart {
    @Override
    public UUID getPartId() {
        return partId;
    }
}
