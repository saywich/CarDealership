package ng.saywich.storageservice.application.contracts.car.operations.models;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.enums.BodyType;
import ng.saywich.shared.domain.car.parts.*;

public record CarModelConfiguration(
    CarOption<Engine> engine,
    CarOption<Transmission> transmission,
    CarOption<BodyType> bodyType,
    CarOption<Rims> rims,
    CarOption<Interior> interior,
    CarOption<Color> color
) {}
