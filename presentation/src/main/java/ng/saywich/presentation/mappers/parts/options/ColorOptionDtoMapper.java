package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Color;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.ColorDto;
import ng.saywich.presentation.mappers.parts.ColorDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ColorOptionDtoMapper {

    @Autowired
    protected ColorDtoMapper mapper;

    @Mapping(target = "carPart", expression = "java(mapper.toColor(dto.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dto.priceDifference()))")
    public abstract CarOption<Color> toOption(CarOptionDto<ColorDto> dto);

    @Mapping(target = "carPart", expression = "java(mapper.toColorDto(option.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(option.priceDifference().value())")
    public abstract CarOptionDto<ColorDto> toOptionDto(CarOption<Color> option);
}