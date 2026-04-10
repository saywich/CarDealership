package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.car.operations.models.CarModelConfiguration;
import ng.saywich.application.contracts.dtos.cars.CarModelDto;

public final class ConfigureCarModel {
    private ConfigureCarModel() {}

    public record Request(
        CarModelDto model,
        CarModelConfiguration configuration
    ) {}

    public record Response(
        CarModelDto model
    ) {}
}
