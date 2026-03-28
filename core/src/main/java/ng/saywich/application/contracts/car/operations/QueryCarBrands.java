package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarBrand;

import java.util.List;
import java.util.UUID;

public final class QueryCarBrands {
    private QueryCarBrands() {}

    public record Request(UUID[] ids) {}

    public record Response(List<CarBrand> brands) {}
}