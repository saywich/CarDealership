package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarDto;

import java.util.List;
import java.util.UUID;

public final class QueryCars {
    private QueryCars() {}

    public record Request(UUID[] carIds) {}

    public record Response(List<CarDto> cars) {}
}