package ng.saywich.shared.domain.car;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import ng.saywich.shared.domain.car.parts.OptionSet;
import ng.saywich.shared.domain.exceptions.DomainValidationException;
import ng.saywich.shared.domain.value.Price;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class CarModel {
    @NonNull UUID modelId;

    @NonNull String name;

    @NonNull
    CarBrand brand;

    @NonNull
    OptionSet optionSet;

    @NonNull Price basePrice;

    public Price totalPrice() {
        return new Price(
            basePrice.value()
            .add(optionSet.engine().priceDifference().value())
            .add(optionSet.transmission().priceDifference().value())
            .add(optionSet.bodyType().priceDifference().value())
            .add(optionSet.rims().priceDifference().value())
            .add(optionSet.interior().priceDifference().value())
            .add(optionSet.color().priceDifference().value())
        );
    }

    public static class CarModelBuilder {
        private <T> T requireNotNull(T value) throws DomainValidationException {
            if (value == null) {
                throw new DomainValidationException("Required value is not set");
            }

            return value;
        }

        public CarModel build() throws DomainValidationException {
            return new CarModel(
                requireNotNull(this.modelId),
                requireNotNull(this.name),
                requireNotNull(this.brand),
                requireNotNull(this.optionSet),
                requireNotNull(this.basePrice)
            );
        }
    }
}
