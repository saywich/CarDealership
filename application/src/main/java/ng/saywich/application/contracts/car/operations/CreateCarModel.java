package ng.saywich.application.contracts.car.operations;

import ng.saywich.domain.car.CarBrand;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.value.Price;

import java.math.BigDecimal;
import java.util.UUID;

public final class CreateCarModel {
    private CreateCarModel() {}

    public record Request(
        String name,
        UUID brandId,
        UUID engineId,
        UUID transmissionId,
        BodyType bodyType,
        UUID rimsId,
        UUID interiorId,
        UUID colorId,
        BigDecimal basePrice
    ) {}

    public record Response(CarModel model) {}
}
