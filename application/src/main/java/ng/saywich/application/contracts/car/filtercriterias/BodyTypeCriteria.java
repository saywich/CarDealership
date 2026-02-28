package ng.saywich.application.contracts.car.filtercriterias;

import lombok.AllArgsConstructor;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.enums.BodyType;

import java.util.List;

public record BodyTypeCriteria(BodyType bodyType) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> m.getBodyType().carPart() == bodyType)
            .toList();
    }
}
