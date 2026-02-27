package ng.saywich.application.contracts.car.operations.models;

import lombok.Builder;
import ng.saywich.application.value.CarFullName;
import ng.saywich.application.value.Range;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.car.enums.TransmissionType;
import ng.saywich.domain.value.Price;

@Builder
public class CarModelFilter {
    Range<Price> priceRange;

    CarFullName carFullName;

    BodyType bodyType;

    FuelType fuelType;

    Range<Integer> powerRange;

    Range<Integer> volumeRange;

    TransmissionType transmissionType;

    DriveTrain driveTrain;

    String colorName;
}
