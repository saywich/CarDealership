package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarModel;

import java.util.UUID;

public final class DeleteCarModel {
    private DeleteCarModel() {}

    public record Request(
        UUID modelId
    ) {}

    public record Response(CarModel model) {}
}