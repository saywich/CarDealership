package ng.saywich.application.contracts.car.operations.models;

import ng.saywich.domain.car.CarBrand;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.value.Price;

public record CarModelConfiguration(
    CarOption<Engine> engine,
    CarOption<Transmission> transmission,
    CarOption<BodyType> bodyType,
    CarOption<Rims> rims,
    CarOption<Interior> interior,
    CarOption<Color> color
) {}
