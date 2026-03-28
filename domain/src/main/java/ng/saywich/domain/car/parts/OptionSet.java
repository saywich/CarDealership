package ng.saywich.domain.car.parts;

import lombok.Builder;
import lombok.NonNull;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.exceptions.DomainValidationException;

import java.util.UUID;

@Builder(toBuilder = true)
public record OptionSet(
    @NonNull UUID optionSetId,
    @NonNull CarOption<Engine> engine,
    @NonNull CarOption<Transmission> transmission,
    @NonNull CarOption<BodyType> bodyType,
    @NonNull CarOption<Rims> rims,
    @NonNull CarOption<Interior> interior,
    @NonNull CarOption<Color> color
) {
    public static class OptionSetBuilder {
        private <T> T requireNotNull(T value) throws DomainValidationException {
            if (value == null) {
                throw new DomainValidationException("Required value is not set");
            }

            return value;
        }

        public OptionSet build() throws DomainValidationException {
            return new OptionSet(
                requireNotNull(this.optionSetId),
                requireNotNull(this.engine),
                requireNotNull(this.transmission),
                requireNotNull(this.bodyType),
                requireNotNull(this.rims),
                requireNotNull(this.interior),
                requireNotNull(this.color)
            );
        }
    }
}
