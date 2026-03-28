package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.presentation.dtos.cars.CarDto;

public final class UpdateCar {
    private UpdateCar() {}

    public record Request(CarDto car) {}

    public record Response(CarDto car) {}
}