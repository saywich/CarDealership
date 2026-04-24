package ng.saywich.storageservice.application.abstractions.car;

import ng.saywich.shared.domain.car.CarBrand;

import java.util.List;
import java.util.UUID;

public interface ICarBrandRepository {
    public List<CarBrand> query(UUID[] brandIds);

    public void create(CarBrand brand);

    public void update(CarBrand brand);

    public void delete(UUID brandId);
}
