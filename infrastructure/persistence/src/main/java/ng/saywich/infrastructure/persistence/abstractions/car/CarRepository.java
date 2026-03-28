package ng.saywich.infrastructure.persistence.abstractions.car;

import ng.saywich.application.abstractions.car.ICarRepository;
import ng.saywich.domain.car.Car;
import ng.saywich.infrastructure.persistence.jparepositories.cars.ICarDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.cars.CarMapper;
import ng.saywich.infrastructure.persistence.models.cars.CarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarRepository implements ICarRepository {
    @Autowired
    private ICarDaoRepository carDaoRepository;

    @Autowired
    private CarMapper mapper;

    @Override
    public List<Car> query(UUID[] carIds) {
        return carDaoRepository.findAllById(List.of(carIds)).stream()
            .map(c -> mapper.toCar(c))
            .toList();
    }

    @Override
    public List<Car> queryPage(UUID[] carIds, int pageSize, int page) {
        return List.of();
    }

    @Override
    public void createCar(Car car) {
        carDaoRepository.save(mapper.toCarDao(car));
    }

    @Override
    public void updateCar(Car car) {
        carDaoRepository.save(mapper.toCarDao(car));
    }

    @Override
    public void deleteCar(UUID carId) {
        Optional<CarDao> dao = carDaoRepository.findById(carId);

        dao.ifPresent(c -> {
            CarDao removedDao = CarDao.builder()
                .id(c.getId())
                .vinNumber(c.getVinNumber())
                .carModel(c.getCarModel())
                .optionSet(c.getOptionSet())
                .updatedAt(c.getUpdatedAt())
                .createdAt(c.getCreatedAt())
                .removed(true)
                .build();

            carDaoRepository.save(removedDao);
        });
    }
}
