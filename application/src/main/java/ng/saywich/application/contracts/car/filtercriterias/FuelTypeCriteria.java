package ng.saywich.application.contracts.car.filtercriterias;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.enums.FuelType;

import java.util.List;

public record FuelTypeCriteria(FuelType fuelType) implements ICriteria {
    @Override
    public List<CarModel> meetCriteria(List<CarModel> models) {
        return models.stream()
            .filter(m -> m.getEngine().carPart().fuelType() == fuelType)
            .toList();
    }
}
