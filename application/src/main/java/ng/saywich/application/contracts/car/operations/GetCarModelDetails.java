package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.AvailableOptions;
import ng.saywich.domain.car.CarModel;

import java.util.UUID;

public final class GetCarModelDetails {
    private GetCarModelDetails() {}

    public record Request(UUID modelId) {}

    public record Response(CarModel model, AvailableOptions options) {}
}
