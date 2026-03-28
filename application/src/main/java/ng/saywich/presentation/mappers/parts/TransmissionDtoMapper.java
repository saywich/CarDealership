package ng.saywich.presentation.mappers.parts;

import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.presentation.dtos.parts.TransmissionDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransmissionDtoMapper {
    Transmission toTransmission(TransmissionDto dto);

    TransmissionDto toTransmissionDto(Transmission transmission);
}
