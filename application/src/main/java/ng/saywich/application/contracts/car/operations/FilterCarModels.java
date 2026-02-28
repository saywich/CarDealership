package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.car.filtercriterias.ICriteria;
import ng.saywich.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.domain.car.CarModel;

import java.util.List;

public final class FilterCarModels {
    private FilterCarModels() {}

    public record Request(ICriteria criteria) {}

    public record Response(List<CarModel> models) {}
}
