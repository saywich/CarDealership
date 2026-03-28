package ng.saywich.presentation.mappers.parts;

import ng.saywich.domain.car.parts.Color;
import ng.saywich.presentation.dtos.parts.ColorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColorDtoMapper {
    Color toColor(ColorDto dto);

    ColorDto toColorDto(Color color);
}
