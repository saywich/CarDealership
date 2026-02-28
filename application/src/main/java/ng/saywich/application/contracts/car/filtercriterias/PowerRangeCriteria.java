package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.application.value.Range;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.value.HorsePower;

import java.util.List;

public record PowerRangeCriteria(Range<HorsePower> powerRange) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> {
                HorsePower modelPower = m.getEngine().carPart().power();

                return modelPower.value() >= powerRange.start().value() &&
                    modelPower.value() <= powerRange.end().value();
            })
            .toList();
    }
}
