package ng.saywich.domain.car;

import ng.saywich.domain.value.Price;

import java.math.BigDecimal;
import java.util.UUID;

public record CarOption<T>(
    UUID optionId,
    T carPart,
    Price priceDifference
) {
    public static <T> CarOption<T> zero(UUID id, T part) {
        return new CarOption<T>(id, part, new Price(new BigDecimal(0)));
    }
}
