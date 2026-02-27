package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarModel;

import java.util.UUID;

public final class DeleteCar {
    private DeleteCar() {}

    public record Request(
        UUID carId
    ) {}

    public record Response() {}
}
