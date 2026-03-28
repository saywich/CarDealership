package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarBrand;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.value.Price;
import ng.saywich.presentation.dtos.cars.CarDto;

public final class CreateCar {
    private CreateCar() {}

    public record Request(
        String vinNumber,
        String name,
        CarBrand brand,
        CarOption<Engine> engine,
        CarOption<Transmission> transmission,
        CarOption<BodyType> bodyType,
        CarOption<Rims> rims,
        CarOption<Interior> interior,
        CarOption<Color> color,
        Price basePrice
    ) {}

    public record Response(CarDto car) {}
}