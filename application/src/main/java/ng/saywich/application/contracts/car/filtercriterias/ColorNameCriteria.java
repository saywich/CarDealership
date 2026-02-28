package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.domain.car.CarModel;

import java.util.List;

public record ColorNameCriteria(String colorName) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> m.getColor().carPart().name() == colorName)
            .toList();
    }
}
