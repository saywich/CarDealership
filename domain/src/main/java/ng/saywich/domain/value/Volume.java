package ng.saywich.domain.value;

import ng.saywich.domain.exceptions.InvalidValue;

public record Volume(double value) {
    public Volume {
        if (value < 0) {
            throw new InvalidValue("Volume can't be negative.");
        }
    }
}
