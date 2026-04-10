package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.dtos.cars.CarBrandDto;

public final class CreateCarBrand {
    private CreateCarBrand() {}

    public record Request(String name) {}

    public record Response(CarBrandDto brand) {}
}
