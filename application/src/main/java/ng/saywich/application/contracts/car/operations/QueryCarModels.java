package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarModel;

import java.util.List;
import java.util.UUID;

public final class QueryCarModels {
    private QueryCarModels() {}

    public record Request(UUID[] modelIds) {}

    public record Response(List<CarModel> models) {}
}
