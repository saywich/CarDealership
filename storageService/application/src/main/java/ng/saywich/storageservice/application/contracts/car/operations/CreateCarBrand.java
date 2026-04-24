package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarBrandDto;

public final class CreateCarBrand {
    private CreateCarBrand() {}

    public record Request(String name) {}

    public record Response(CarBrandDto brand) {}
}
