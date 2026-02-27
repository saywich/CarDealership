package ng.saywich.application.abstractions.car;

import ng.saywich.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.domain.car.AvailableOptions;
import ng.saywich.domain.car.CarModel;

import java.util.List;
import java.util.UUID;

public interface ICarModelRepository {
    public List<CarModel> query(UUID[] carModelIds);

    public List<CarModel> queryWithFilter(CarModelFilter filter);

    public void createCarModel(CarModel modelDto);

    public void updateCarModel(CarModel modelDto);

    public void deleteCarModel(UUID modelId);

    public AvailableOptions getAvailableOptionsForModel(UUID modelId);
}
