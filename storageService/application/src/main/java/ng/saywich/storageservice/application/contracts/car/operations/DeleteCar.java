package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarDto;

import java.util.UUID;

public final class DeleteCar {
    private DeleteCar() {}

    public record Request(
        UUID carId
    ) {}

    public record Response(CarDto car) {}
}
