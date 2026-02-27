package ng.saywich.domain.car;

import ng.saywich.domain.value.Price;

public record CarOption<T>(
    T carPart,
    Price priceDifference
) {}
