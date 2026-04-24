package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.car.operations.models.CarModelConfiguration;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelDto;

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
