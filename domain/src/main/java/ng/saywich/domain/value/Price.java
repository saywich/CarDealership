package ng.saywich.domain.value;

import ng.saywich.domain.exceptions.InvalidValue;

import java.math.BigDecimal;

public record Price(BigDecimal value) {
    public Price {
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new InvalidValue("Price can't be negative");
        }
    }
}