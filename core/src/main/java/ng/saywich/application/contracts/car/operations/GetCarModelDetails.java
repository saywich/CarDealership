package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.dtos.cars.CarModelDto;
import ng.saywich.domain.car.AvailableOptions;

import java.util.UUID;

public final class GetCarModelDetails {
    private GetCarModelDetails() {}

    public record Request(UUID modelId) {}

    public record Response(CarModelDto model, AvailableOptions options) {}
}
