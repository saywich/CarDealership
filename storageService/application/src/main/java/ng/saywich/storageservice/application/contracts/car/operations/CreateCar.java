package ng.saywich.storageservice.application.contracts.car.operations;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarBrandDto;
import ng.saywich.storageservice.application.contracts.dtos.cars.CarDto;
import ng.saywich.storageservice.application.contracts.dtos.parts.*;
import ng.saywich.shared.domain.car.enums.BodyType;

import java.math.BigDecimal;

public final class CreateCar {
    private CreateCar() {}

    public record Request(
        String vinNumber,
        String name,
        CarBrandDto brand,
        CarOptionDto<EngineDto> engine,
        CarOptionDto<TransmissionDto> transmission,
        CarOptionDto<BodyType> bodyType,
        CarOptionDto<RimsDto> rims,
        CarOptionDto<InteriorDto> interior,
        CarOptionDto<ColorDto> color,
        BigDecimal basePrice
    ) {}

    public record Response(CarDto car) {}
}