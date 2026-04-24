package ng.saywich.storageservice.application.abstractions.car;

import ng.saywich.storageservice.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.shared.domain.car.AvailableOptions;
import ng.saywich.shared.domain.car.CarModel;

import java.util.List;
import java.util.UUID;

public interface ICarModelRepository {
    public List<CarModel> query(UUID[] carModelIds);

    public List<CarModel> queryPage(UUID[] carModelIds, int pageSize, int page);

    public List<CarModel> queryPageWithFilter(CarModelFilter filter, int pageSize, int page);

    public void createCarModel(CarModel modelDto);

    public void updateCarModel(CarModel modelDto);

    public void deleteCarModel(UUID modelId);

    public AvailableOptions getAvailableOptionsForModel(UUID modelId);
}
