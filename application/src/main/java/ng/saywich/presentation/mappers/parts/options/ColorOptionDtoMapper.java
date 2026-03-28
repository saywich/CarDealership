package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Color;
import ng.saywich.domain.value.Price;
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

    public CarOption<Color> toOption(CarOptionDto<ColorDto> dto) {
        return new CarOption<Color>(
            dto.optionId(),
            mapper.toColor(dto.carPart()),
            new Price(dto.priceDifference())
        );
    }

    public CarOptionDto<ColorDto> toOptionDto(CarOption<Color> option) {
        return new CarOptionDto<ColorDto>(
            option.optionId(),
            mapper.toColorDto(option.carPart()),
            option.priceDifference().value()
        );
    }
}