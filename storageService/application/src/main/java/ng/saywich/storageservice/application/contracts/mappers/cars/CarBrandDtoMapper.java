package ng.saywich.storageservice.application.contracts.mappers.cars;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarBrandDto;
import ng.saywich.shared.domain.car.CarBrand;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarBrandDtoMapper {
    CarBrand toCarBrand(CarBrandDto dto);

    CarBrandDto toCarBrandDto(CarBrand brand);
}
