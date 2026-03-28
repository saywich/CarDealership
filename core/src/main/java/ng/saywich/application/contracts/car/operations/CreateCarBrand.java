package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarBrand;

public final class CreateCarBrand {
    private CreateCarBrand() {}

    public record Request(String name) {}

    public record Response(CarBrand brand) {}
}
