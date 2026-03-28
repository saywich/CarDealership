package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.car.operations.models.CarModelConfiguration;
import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarModelDto;

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
