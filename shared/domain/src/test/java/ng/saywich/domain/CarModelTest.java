package ng.saywich.domain;

import ng.saywich.shared.domain.car.AvailableOptions;
import ng.saywich.shared.domain.car.CarModel;
import ng.saywich.shared.domain.car.CarModelConfigurer;
import ng.saywich.shared.domain.car.enums.FuelType;
import ng.saywich.shared.domain.car.parts.*;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.value.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class CarModelTest {
    @Test void carModelBuilderTest() {
        CarModelMockRepository repository = new CarModelMockRepository();
        CarModel.CarModelBuilder builder = repository.getModel().toBuilder();

        CarModel model = builder.build();

        Assertions.assertEquals(FuelType.PETROL, model.getOptionSet().engine().carPart().fuelType());
        Assertions.assertEquals(new Price(new BigDecimal(500_000)), model.getBasePrice());
    }

    @Test void carModelPartialBuilderThrowsTest() {
        CarModel.CarModelBuilder builder = CarModel.builder();

        builder.modelId(UUID.randomUUID());
        builder.name("m5");

        Assertions.assertThrows(DomainValidationException.class, builder::build);
    }

    @Test void carModelConfigurerTest() {
        CarModelMockRepository repository = new CarModelMockRepository();

        CarModel carModel = repository.getModel();

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
            carModel.getOptionSet().toBuilder()
        );

        Assertions.assertDoesNotThrow(
            () -> configurer.setEngine(availableOptions.availableEngines().get(1)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setTransmission(availableOptions.availableTransmissions().get(1)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setBodyType(availableOptions.availableBodyTypes().get(0)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setRims(availableOptions.availableRims().get(0)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setInterior(availableOptions.availableInteriors().get(0)));

        Assertions.assertDoesNotThrow(
            () -> configurer.setColor(availableOptions.availableColors().get(0)));


        CarModel model = carModel.toBuilder().optionSet(configurer.configure()).build();

        Assertions.assertEquals(
            new Price(model.getBasePrice().value().add(new BigDecimal(200_000))),
            model.totalPrice()
        );
    }
}
