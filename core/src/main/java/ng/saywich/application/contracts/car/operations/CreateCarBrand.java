package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarBrand;
import ng.saywich.presentation.dtos.cars.CarBrandDto;

public final class CreateCarBrand {
    private CreateCarBrand() {}

    public record Request(String name) {}

    public record Response(CarBrandDto brand) {}
}
