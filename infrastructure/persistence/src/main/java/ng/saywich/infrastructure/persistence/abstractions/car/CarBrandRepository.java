package ng.saywich.infrastructure.persistence.abstractions.car;

import ng.saywich.application.abstractions.car.ICarBrandRepository;
import ng.saywich.domain.car.CarBrand;
import ng.saywich.infrastructure.persistence.jparepositories.cars.ICarBrandDaoRepository;
import ng.saywich.infrastructure.persistence.mappers.cars.CarBrandMapper;
import ng.saywich.infrastructure.persistence.models.cars.CarBrandDao;
import ng.saywich.infrastructure.persistence.models.cars.CarModelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CarBrandRepository implements ICarBrandRepository {
    @Autowired
    private ICarBrandDaoRepository carBrandDaoRepository;

    @Autowired
    private CarBrandMapper carBrandMapper;

    @Override
    public List<CarBrand> query(UUID[] brandIds) {
        List<CarBrandDao> modelDaoList = brandIds.length == 0
            ? carBrandDaoRepository.findAll()
            : carBrandDaoRepository.findAllById(Arrays.stream(brandIds).toList());

        return modelDaoList.stream()
            .map(carBrandMapper::toCarBrand)
            .toList();
    }

    @Override
    public void create(CarBrand brand) {
        carBrandDaoRepository.save(carBrandMapper.toCarBrandDao(brand));
    }

    @Override
    public void update(CarBrand brand) {
        carBrandDaoRepository.save(carBrandMapper.toCarBrandDao(brand));
    }

    @Override
    public void delete(UUID brandId) {
        Optional<CarBrandDao> dao = carBrandDaoRepository.findById(brandId);
        dao.ifPresent(b -> carBrandDaoRepository.save(b.toBuilder()
            .removed(true).build()
        ));
    }
}
