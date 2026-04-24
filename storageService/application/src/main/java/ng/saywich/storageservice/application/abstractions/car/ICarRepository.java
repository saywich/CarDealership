package ng.saywich.storageservice.application.abstractions.car;

import ng.saywich.shared.domain.car.Car;

import java.util.List;
import java.util.UUID;

public interface ICarRepository {
    public List<Car> query(UUID[] carIds);

    public List<Car> queryPage(UUID[] carIds, int pageSize, int page);

    public void createCar(Car car);

    public void updateCar(Car car);

    public void deleteCar(UUID carId);
}
