package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.ICarPart;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BodyTypeOptionDtoMapper {
    @Mapping(target = "carPart", expression = "java(dto.carPart())")
    @Mapping(target = "priceDifference", expression = "java(new Price(dto.priceDifference()))")
    public abstract CarOption<BodyType> toOption(CarOptionDto<BodyType> dto);

    @Mapping(target = "carPart", expression = "java(option.carPart())")
    @Mapping(target = "priceDifference", expression = "java(option.priceDifference().value())")
    public abstract CarOptionDto<BodyType> toOptionDto(CarOption<BodyType> option);
}