package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;

import java.util.List;
import java.util.UUID;

public final class QueryCars {
    private QueryCars() {}

    public record Request(UUID[] carIds) {}

    public record Response(List<Car> cars) {}
}