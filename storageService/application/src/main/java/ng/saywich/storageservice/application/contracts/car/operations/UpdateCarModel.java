package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelDto;

public final class UpdateCarModel {
    private UpdateCarModel() {}

    public record Request(CarModelDto model) {}

    public record Response(CarModelDto model) {}
}
