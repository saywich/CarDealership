package ng.saywich.storageservice.infrastructure.persistence.mappers.cars;

import ng.saywich.shared.domain.car.CarBrand;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.CarBrandDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarBrandMapper {
    public CarBrand toCarBrand(CarBrandDao dao);

    public CarBrandDao toCarBrandDao(CarBrand brand);
}
