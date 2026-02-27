package ng.saywich.domain.car.parts;

import java.util.UUID;

public record Color(
    UUID partId,
    String name,
    int hexCode
) implements ICarPart {
    @Override
    public UUID getPartId() {
        return partId;
    }
}
