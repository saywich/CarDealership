package ng.saywich.presentation.dtos.cars;

import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.car.enums.TransmissionType;

import java.math.BigDecimal;

public record CarModelFilterDto(
    BigDecimal minPrice,
    BigDecimal maxPrice,
    String brandName,
    String modelName,
    BodyType bodyType,
    FuelType fuelType,
    Integer minPower,
    Integer maxPower,
    Double minVolume,
    Double maxVolume,
    TransmissionType transmissionType,
    DriveTrain driveTrain,
    String colorName
) {}
