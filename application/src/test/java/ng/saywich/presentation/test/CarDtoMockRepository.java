package ng.saywich.presentation.test;

import ng.saywich.application.contracts.dtos.cars.CarModelDto;
import ng.saywich.application.contracts.mappers.cars.CarModelDtoMapper;
import ng.saywich.domain.car.CarBrand;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.car.enums.TransmissionType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Price;
import ng.saywich.domain.value.Volume;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.UUID;

public class CarDtoMockRepository {
    private final CarModelDtoMapper mapper;

    public CarDtoMockRepository(CarModelDtoMapper dtoMapper) {
        mapper = dtoMapper;
    }
    public CarModelDto getModel() {
        CarModel.CarModelBuilder builder = CarModel.builder();
        OptionSet.OptionSetBuilder optionSetBuilder = OptionSet.builder();

        optionSetBuilder.optionSetId(UUID.randomUUID());
        optionSetBuilder.bodyType(new CarOption<BodyType>(
            UUID.randomUUID(), BodyType.SEDAN, new Price(new BigDecimal(0))));
        optionSetBuilder.engine(new CarOption<Engine>(
                UUID.randomUUID(),
                new Engine(
                    UUID.randomUUID(),
                    "shesnar'",
                    new HorsePower(88),
                    new Volume(1.6),
                    FuelType.PETROL
                ),
                new Price(new BigDecimal(0))
            )
        );
        optionSetBuilder.transmission(
            new CarOption<Transmission>(
                UUID.randomUUID(),
                new Transmission(
                    UUID.randomUUID(),
                    "mehanika",
                    5,
                    TransmissionType.MANUAL,
                    DriveTrain.FRONT
                ),
                new Price(new BigDecimal(0))
            )
        );
        optionSetBuilder.rims(
            new CarOption<Rims>(
                UUID.randomUUID(),
                new Rims(UUID.randomUUID(), "shtampovki", 14),
                new Price(new BigDecimal(0))
            )
        );
        optionSetBuilder.interior(
            new CarOption<Interior>(
                UUID.randomUUID(),
                new Interior(UUID.randomUUID(), "tryapka"),
                new Price(new BigDecimal(0))
            )
        );
        optionSetBuilder.color(
            new CarOption<Color>(
                UUID.randomUUID(),
                new Color(UUID.randomUUID(), "backlazhan", 0xFFFFFF),
                new Price(new BigDecimal(0))
            )
        );

        builder.modelId(UUID.randomUUID());
        builder.brand(new CarBrand(UUID.randomUUID(), "LADA"));
        builder.name("Priora");
        builder.optionSet(optionSetBuilder.build());
        builder.basePrice(new Price(new BigDecimal(500_000)));

        return mapper.toCarModelDto(builder.build());
    }
}
