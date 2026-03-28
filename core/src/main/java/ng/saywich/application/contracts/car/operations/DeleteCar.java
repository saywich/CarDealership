package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarDto;

import java.util.UUID;

public final class DeleteCar {
    private DeleteCar() {}

    public record Request(
        UUID carId
    ) {}

    public record Response(CarDto car) {}
}
