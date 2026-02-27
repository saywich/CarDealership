package ng.saywich;

import ng.saywich.domain.car.AvailableOptions;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.CarModelConfigurer;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.exceptions.DomainValidationException;
import ng.saywich.domain.value.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CarModelTest {
    @Test void carModelBuilderTest() {
        CarModelMockRepository repository = new CarModelMockRepository();
        CarModel.CarModelBuilder builder = repository.getModel().toBuilder();

        CarModel model = builder.build();

        Assertions.assertEquals(FuelType.PETROL, model.getEngine().carPart().fuelType());
        Assertions.assertEquals(new Price(500_000), model.getBasePrice());
    }

    @Test void carModelPartialBuilderThrowsTest() {
        CarModel.CarModelBuilder builder = CarModel.builder();

        builder.modelId(UUID.randomUUID());
        builder.rims(new CarOption<Rims>(
            new Rims(UUID.randomUUID(), "bbs", 21),
            new Price(100_000))
        );

        Assertions.assertThrows(DomainValidationException.class, builder::build);
    }

    @Test void carModelConfigurerTest() {
        CarModelMockRepository repository = new CarModelMockRepository();

        CarModel.CarModelBuilder modelBuilder = repository.getModel().toBuilder();

        AvailableOptions availableOptions = new AvailableOptions(
            repository.getAvailableEngines(),
            repository.getAvailableTransmissions(),
            repository.getAvailableBodyTypes(),
            repository.getAvailableRims(),
            repository.getAvailableInteriors(),
            repository.getAvailableColors()
        );


        CarModelConfigurer configurer = new CarModelConfigurer(
            availableOptions,
            modelBuilder
        );

        Assertions.assertDoesNotThrow(
            () -> configurer.setEngine(availableOptions.availableEngines().get(1)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setTransmission(availableOptions.availableTransmissions().get(1)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setBodyType(availableOptions.availableBodyTypes().getFirst()));

        Assertions.assertDoesNotThrow(
            () -> configurer.setRims(availableOptions.availableRims().getFirst()));

        Assertions.assertDoesNotThrow(
            () -> configurer.setInterior(availableOptions.availableInteriors().getFirst()));

        Assertions.assertDoesNotThrow(
            () -> configurer.setColor(availableOptions.availableColors().getFirst()));


        CarModel model = configurer.configure();

        Assertions.assertEquals(new Price(model.getBasePrice().value() + 200_000), model.totalPrice());
    }
}
