package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.application.value.Range;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Price;

import java.util.List;

public record PriceRangeCriteria(Range<Price> priceRange) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> {
                Price modelTotalPrice = m.totalPrice();
                return modelTotalPrice.value().compareTo(priceRange.start().value()) >= 0 &&
                    modelTotalPrice.value().compareTo(priceRange.end().value()) <= 0;
            })
            .toList();
    }
}
