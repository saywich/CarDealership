package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.Car;

public final class UpdateCar {
    private UpdateCar() {}

    public record Request(Car car) {}

    public record Response(Car car) {}
}