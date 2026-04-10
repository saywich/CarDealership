package ng.saywich.application.contracts.mappers.cars;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.value.Price;
import ng.saywich.application.contracts.dtos.cars.CarModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    imports = Price.class
)
public abstract class CarModelDtoMapper {
    @Autowired
    protected CarBrandDtoMapper carBrandDtoMapper;

    @Autowired
    protected OptionSetDtoMapper optionSetDtoMapper;

    @Mapping(target = "basePrice", expression = "java(new Price(dto.basePrice()))")
    @Mapping(target = "brand", expression = "java(carBrandDtoMapper.toCarBrand(dto.brand()))")
    @Mapping(target = "optionSet", expression = "java(optionSetDtoMapper.toOptionSet(dto.optionSet()))")
    public abstract CarModel toCarModel(CarModelDto dto);

    @Mapping(target = "basePrice", expression = "java(model.getBasePrice().value())")
    @Mapping(target = "brand", expression = "java(carBrandDtoMapper.toCarBrandDto(model.getBrand()))")
    @Mapping(target = "optionSet", expression = "java(optionSetDtoMapper.toOptionSetDto(model.getOptionSet()))")
    public abstract CarModelDto toCarModelDto(CarModel model);
}
