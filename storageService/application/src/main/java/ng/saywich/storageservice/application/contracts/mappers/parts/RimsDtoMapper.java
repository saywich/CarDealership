package ng.saywich.storageservice.application.contracts.mappers.parts;

import ng.saywich.storageservice.application.contracts.dtos.parts.RimsDto;
import ng.saywich.shared.domain.car.parts.Rims;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RimsDtoMapper {
    Rims toRims(RimsDto dto);

    RimsDto toRimsDto(Rims rims);
}
