package ng.saywich.application.contracts.carparts.operations;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.value.Price;

import java.util.UUID;

public final class LinkToCarModel {
    private LinkToCarModel() {}

    public record Request(
        UUID modelId,
        UUID partId,
        Price priceDifference
    ) {}

    public record Response() {}
}
