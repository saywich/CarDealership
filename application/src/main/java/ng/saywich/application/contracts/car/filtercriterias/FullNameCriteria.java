package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.application.value.CarFullName;
import ng.saywich.domain.car.CarModel;

import java.util.List;

public record FullNameCriteria(CarFullName fullName) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter((m) -> {
                if (fullName.modelName().isPresent()) {
                    return m.getName() == fullName.modelName().get() &&
                        m.getBrand().name() == fullName.brandName();
                } else {
                    return m.getBrand().name() == fullName.brandName();
                }
            })
            .toList();
    }
}
