package ng.saywich.application.contracts.mappers.parts;

import ng.saywich.domain.car.parts.Color;
import ng.saywich.application.contracts.dtos.parts.ColorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColorDtoMapper {
    Color toColor(ColorDto dto);

    ColorDto toColorDto(Color color);
}
