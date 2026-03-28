package ng.saywich.infrastructure.persistence.mappers.cars;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.car.parts.OptionSet;
import ng.saywich.infrastructure.persistence.models.cars.CarDao;
import ng.saywich.infrastructure.persistence.models.cars.CarModelDao;
import ng.saywich.infrastructure.persistence.models.cars.OptionSetDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CarMapper {
    @Autowired
    protected OptionSetMapper optionSetMapper;

    @Autowired
    protected CarModelMapper carModelMapper;

    public Car toCar(CarDao dao) {
        CarModel model = carModelMapper.toCarModel(dao.getCarModel());
        OptionSet optionSet = optionSetMapper.toOptionSet(dao.getOptionSet());

        model = model.toBuilder().optionSet(optionSet).build();

        return new Car(dao.getId(), dao.getVinNumber(), model);
    }

    public CarDao toCarDao(Car car) {
        CarModelDao modelDao = carModelMapper.toCarModelDao(car.model());
        OptionSetDao optionSetDao = optionSetMapper.toOptionSetDao(car.model().getOptionSet());

        return CarDao.builder()
            .id(car.carId())
            .vinNumber(car.vinNumber())
            .carModel(modelDao)
            .optionSet(optionSetDao)
            .build();
    }
}
