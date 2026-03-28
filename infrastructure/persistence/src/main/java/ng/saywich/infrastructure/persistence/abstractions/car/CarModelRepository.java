package ng.saywich.infrastructure.persistence.abstractions.car;

import lombok.AllArgsConstructor;
import ng.saywich.application.abstractions.car.ICarModelRepository;
import ng.saywich.application.abstractions.carparts.IOptionSetRepository;
import ng.saywich.application.contracts.car.operations.models.CarModelFilter;
import ng.saywich.domain.car.AvailableOptions;
import ng.saywich.domain.car.CarModel;
import ng.saywich.infrastructure.persistence.jparepositories.cars.ICarModelDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.cars.CarModelMapper;
import ng.saywich.infrastructure.persistence.models.cars.CarModelDao;
import ng.saywich.infrastructure.persistence.specifications.CarModelSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class CarModelRepository implements ICarModelRepository {
    @Autowired
    private ICarModelDaoRepository carModelDaoRepository;

    @Autowired
    private CarModelMapper carModelMapper;

    @Autowired
    private CarOptionsRepository carOptionsRepository;

    @Override
    public List<CarModel> query(UUID[] carModelIds) {
        List<CarModelDao> modelDaoList = carModelIds.length == 0
            ? carModelDaoRepository.findAll()
            : carModelDaoRepository.findAllById(Arrays.stream(carModelIds).toList());

        return modelDaoList.stream()
            .map((model) -> {
                return carModelMapper.toCarModel(model);
            })
            .toList();
    }

    @Override
    public List<CarModel> queryWithFilter(CarModelFilter filter) {
        return carModelDaoRepository.findAll(new CarModelSpecification(filter))
            .stream()
            .map(cm -> carModelMapper.toCarModel(cm))
            .toList();
    }

    @Override
    public void createCarModel(CarModel modelDto) {
        carModelDaoRepository.save(carModelMapper.toCarModelDao(modelDto));
    }

    @Override
    public void updateCarModel(CarModel modelDto) {
        carModelDaoRepository.save(carModelMapper.toCarModelDao(modelDto));
    }

    @Override
    public void deleteCarModel(UUID modelId) {
        Optional<CarModelDao> dao = carModelDaoRepository.findById(modelId);
        dao.ifPresent(carModelDao -> carModelDaoRepository.save(carModelDao.toBuilder()
            .removed(true).build()
        ));
    }

    @Override
    public AvailableOptions getAvailableOptionsForModel(UUID modelId) {
        return carOptionsRepository.getAvailableOptionsForModel(modelId);
    }
}
