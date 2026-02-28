package ng.saywich.domain.car;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.domain.exceptions.DomainValidationException;
import ng.saywich.domain.value.Price;

import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class CarModel {
    @NonNull UUID modelId;

    @NonNull String name;

    @NonNull CarBrand brand;

    @NonNull CarOption<Engine> engine;

    @NonNull CarOption<Transmission> transmission;

    @NonNull CarOption<BodyType> bodyType;

    @NonNull CarOption<Rims> rims;

    @NonNull CarOption<Interior> interior;

    @NonNull CarOption<Color> color;

    @NonNull Price basePrice;

    public Price totalPrice() {
        return new Price(
            basePrice.value()
            .add(engine.priceDifference().value())
            .add(transmission.priceDifference().value())
            .add(bodyType.priceDifference().value())
            .add(rims.priceDifference().value())
            .add(interior.priceDifference().value())
            .add(color.priceDifference().value())
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
                requireNotNull(this.engine),
                requireNotNull(this.transmission),
                requireNotNull(this.bodyType),
                requireNotNull(this.rims),
                requireNotNull(this.interior),
                requireNotNull(this.color),
                requireNotNull(this.basePrice)
            );
        }
    }
}
