package ng.saywich.domain.car.parts;

import java.util.UUID;

public record Rims(
    UUID partId,
    String name,
    int diameter
) implements ICarPart {
    @Override
    public UUID getPartId() {
        return partId;
    }
}
