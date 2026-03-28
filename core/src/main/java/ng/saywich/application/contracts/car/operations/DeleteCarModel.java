package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarModelDto;

import java.util.UUID;

public final class DeleteCarModel {
    private DeleteCarModel() {}

    public record Request(
        UUID modelId
    ) {}

    public record Response(CarModelDto model) {}
}