package ng.saywich.storageservice.application.contracts.mappers.parts;

import ng.saywich.storageservice.application.contracts.dtos.parts.ColorDto;
import ng.saywich.shared.domain.car.parts.Color;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColorDtoMapper {
    Color toColor(ColorDto dto);

    ColorDto toColorDto(Color color);
}
