package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarModelDto;

import java.util.List;

public final class UpdateCarModel {
    private UpdateCarModel() {}

    public record Request(CarModelDto model) {}

    public record Response(CarModelDto model) {}
}
