package ng.saywich.storageservice.infrastructure.persistence.abstractions.car;

import ng.saywich.storageservice.application.abstractions.car.ICarRepository;
import ng.saywich.shared.domain.car.Car;
import ng.saywich.storageservice.infrastructure.persistence.jparepositories.cars.ICarDaoRepository;
import ng.saywich.storageservice.infrastructure.persistence.mappers.cars.CarMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarDao;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
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
        int clampedPageSize = Math.max(1, Math.min(100, pageSize));

        List<CarDao> carDaoList = carIds.length == 0
            ? carDaoRepository.findAll(Pageable.ofSize(clampedPageSize).withPage(page - 1)).toList()
            : carDaoRepository.findAllById(Arrays.stream(carIds).toList());

        return carDaoList.stream()
            .map(mapper::toCar)
            .toList();
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
