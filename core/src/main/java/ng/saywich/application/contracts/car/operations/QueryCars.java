package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;
import ng.saywich.presentation.dtos.cars.CarDto;

import java.util.List;
import java.util.UUID;

public final class QueryCars {
    private QueryCars() {}

    public record Request(UUID[] carIds) {}

    public record Response(List<CarDto> cars) {}
}