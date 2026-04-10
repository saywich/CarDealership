package ng.saywich.application.contracts.mappers.parts;

import ng.saywich.domain.car.parts.Interior;
import ng.saywich.application.contracts.dtos.parts.InteriorDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InteriorDtoMapper {
    Interior toInterior(InteriorDto dto);

    InteriorDto toInteriorDto(Interior interior);
}
