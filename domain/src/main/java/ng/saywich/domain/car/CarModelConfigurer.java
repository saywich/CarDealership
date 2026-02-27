package ng.saywich.domain.car;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.exceptions.IncompatibleComponentException;

import java.util.*;

@AllArgsConstructor
public class CarModelConfigurer {
    @Getter
    AvailableOptions availableOptions;

    private final CarModel.CarModelBuilder modelBuilder;

    public CarModelConfigurer setEngine(
        CarOption<Engine> engine
    ) throws IncompatibleComponentException {
        if (!availableOptions.availableEngines().contains(engine)) {
            throw new IncompatibleComponentException(
                "Engine is not compatible with this model.");
        }

        modelBuilder.engine(engine);

        return this;
    }

    public CarModelConfigurer setTransmission(
        CarOption<Transmission> transmission
    ) throws IncompatibleComponentException {
        if (!availableOptions.availableTransmissions().contains(transmission)) {
            throw new IncompatibleComponentException(
                "Transmission is not compatible with this model."
            );
        }

        modelBuilder.transmission(transmission);

        return this;
    }

    public CarModelConfigurer setBodyType(
        CarOption<BodyType> bodyType
    ) throws IncompatibleComponentException {
        if (!availableOptions.availableBodyTypes().contains(bodyType)) {
            throw new IncompatibleComponentException(
                "Body type is not compatible with this model."
            );
        }

        modelBuilder.bodyType(bodyType);

        return this;
    }

    public CarModelConfigurer setRims(
        CarOption<Rims> rims
    ) throws IncompatibleComponentException {
        if (!availableOptions.availableRims().contains(rims)) {
            throw new IncompatibleComponentException(
                "Rims is not compatible with this model."
            );
        }

        modelBuilder.rims(rims);

        return this;
    }

    public CarModelConfigurer setInterior(
        CarOption<Interior> interior
    ) throws IncompatibleComponentException {
        if (!availableOptions.availableInteriors().contains(interior)) {
            throw new IncompatibleComponentException(
                "Interior is not compatible with this model."
            );
        }

        modelBuilder.interior(interior);

        return this;
    }

    public CarModelConfigurer setColor(
        CarOption<Color> color
    ) throws IncompatibleComponentException {
        if (!availableOptions.availableColors().contains(color)) {
            throw new IncompatibleComponentException(
                "Color is not compatible with this model."
            );
        }

        modelBuilder.color(color);

        return this;
    }

    public CarModel configure() {
        return modelBuilder.build();
    }
}
