package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelDto;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarModelFilterDto;

import java.util.List;

public final class FilterCarModels {
    private FilterCarModels() {}

    public record Request(CarModelFilterDto filter) {}

    public record Response(List<CarModelDto> models) {}
}
