package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarModelDto;
import ng.saywich.presentation.dtos.cars.CarModelFilterDto;

import java.util.List;

public final class FilterCarModels {
    private FilterCarModels() {}

    public record Request(CarModelFilterDto filter) {}

    public record Response(List<CarModelDto> models) {}
}
