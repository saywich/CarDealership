package ng.saywich.presentation.mappers.parts;

import ng.saywich.domain.car.parts.Rims;
import ng.saywich.presentation.dtos.parts.RimsDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RimsDtoMapper {
    Rims toRims(RimsDto dto);

    RimsDto toRimsDto(Rims rims);
}
