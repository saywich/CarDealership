package ng.saywich.domain.value;

import ng.saywich.domain.exceptions.InvalidValue;

public record HorsePower(double value) {
    public HorsePower {
        if (value < 0) {
            throw new InvalidValue("Horse power can't be negative");
        }
    }
}
