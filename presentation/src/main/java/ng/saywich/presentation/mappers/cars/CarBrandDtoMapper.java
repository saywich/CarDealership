package ng.saywich.presentation.mappers.cars;

import ng.saywich.domain.car.CarBrand;
import ng.saywich.presentation.dtos.cars.CarBrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CarBrandDtoMapper {
    CarBrand toCarBrand(CarBrandDto dto);

    CarBrandDto toCarBrandDto(CarBrand brand);
}
