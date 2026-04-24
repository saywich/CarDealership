package ng.saywich.shared.domain.value;

import ng.saywich.shared.domain.exceptions.InvalidValue;

public record Volume(double value) {
    public Volume {
        if (value < 0) {
            throw new InvalidValue("Volume can't be negative.");
        }
    }
}
