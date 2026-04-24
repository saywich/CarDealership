package ng.saywich.shared.domain.car.parts;

import java.util.UUID;

public record Interior(
    UUID partId,
    String name
) implements ICarPart {
    @Override
    public UUID getPartId() {
        return partId;
    }
}
