package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarDto;

public final class UpdateCar {
    private UpdateCar() {}

    public record Request(CarDto car) {}

    public record Response(CarDto car) {}
}