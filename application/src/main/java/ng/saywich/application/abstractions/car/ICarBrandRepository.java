package ng.saywich.application.abstractions.car;

import ng.saywich.domain.car.CarBrand;
import ng.saywich.domain.car.CarModel;

import java.util.List;
import java.util.UUID;

public interface ICarBrandRepository {
    public List<CarBrand> query(UUID[] brandIds);

    public void create(CarBrand brand);

    public void update(CarBrand brand);

    public void delete(UUID brandId);
}
