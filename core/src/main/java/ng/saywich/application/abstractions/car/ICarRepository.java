package ng.saywich.application.abstractions.car;

import ng.saywich.domain.car.Car;

import java.util.List;
import java.util.UUID;

public interface ICarRepository {
    public List<Car> query(UUID[] carIds);

    public void createCar(Car car);

    public void updateCar(Car car);

    public void deleteCar(UUID carId);
}
