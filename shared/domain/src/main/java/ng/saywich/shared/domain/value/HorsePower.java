package ng.saywich.shared.domain.value;

import ng.saywich.shared.domain.exceptions.InvalidValue;

public record HorsePower(int value) {
    public HorsePower {
        if (value < 0) {
            throw new InvalidValue("Horse power can't be negative");
        }
    }
}
