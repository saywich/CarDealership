package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.domain.car.CarModel;

import java.util.List;

public final class FilterCarModels {
    private FilterCarModels() {}

    public record Request(CarModelFilter filter) {}

    public record Response(List<CarModel> models) {}
}
