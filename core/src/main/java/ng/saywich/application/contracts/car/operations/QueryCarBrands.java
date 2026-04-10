package ng.saywich.application.contracts.car.operations;

import ng.saywich.application.contracts.dtos.cars.CarBrandDto;

import java.util.List;
import java.util.UUID;

public final class QueryCarBrands {
    private QueryCarBrands() {}

    public record Request(UUID[] ids) {}

    public record Response(List<CarBrandDto> brands) {}
}