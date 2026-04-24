package ng.saywich.shared.domain.car;

import ng.saywich.shared.domain.car.enums.BodyType;
import ng.saywich.shared.domain.car.parts.*;

import java.util.List;

public record AvailableOptions(
    List<CarOption<Engine>> availableEngines,
    List<CarOption<Transmission>> availableTransmissions,
    List<CarOption<BodyType>> availableBodyTypes,
    List<CarOption<Rims>> availableRims,
    List<CarOption<Interior>> availableInteriors,
    List<CarOption<Color>> availableColors
) {}
