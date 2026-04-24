package ng.saywich.storageservice.application.contracts.mappers.parts;

import ng.saywich.storageservice.application.contracts.dtos.parts.InteriorDto;
import ng.saywich.shared.domain.car.parts.Interior;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InteriorDtoMapper {
    Interior toInterior(InteriorDto dto);

    InteriorDto toInteriorDto(Interior interior);
}
