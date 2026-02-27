package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.car.operations.models.CarModelConfiguration;
import ng.saywich.domain.car.CarModel;

public final class ConfigureCarModel {
    private ConfigureCarModel() {}

    public record Request(
        CarModel model,
        CarModelConfiguration configuration
    ) {}

    public record Response(
        CarModel model
    ) {}
}
