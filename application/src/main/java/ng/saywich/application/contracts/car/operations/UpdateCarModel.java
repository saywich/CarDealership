package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarModel;

import java.util.List;

public final class UpdateCarModel {
    private UpdateCarModel() {}

    public record Request(CarModel model) {}

    public record Response(CarModel model) {}
}
