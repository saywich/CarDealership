package ng.saywich.application.contracts.car.operations.models;

import lombok.Builder;
import ng.saywich.application.value.CarFullName;
import ng.saywich.application.value.Range;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.car.enums.TransmissionType;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Price;
import ng.saywich.domain.value.Volume;

@Builder
public record CarModelFilter(
    Range<Price> priceRange,
    CarFullName carFullName,
    BodyType bodyType,
    FuelType fuelType,
    Range<HorsePower> powerRange,
    Range<Volume> volumeRange,
    TransmissionType transmissionType,
    DriveTrain driveTrain,
    String colorName
) {}
