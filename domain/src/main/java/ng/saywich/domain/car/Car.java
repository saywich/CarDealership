package ng.saywich.domain.car;

import java.util.UUID;

public record Car(
    UUID carId,
    String vinNumber,
    CarModel model
) {}
