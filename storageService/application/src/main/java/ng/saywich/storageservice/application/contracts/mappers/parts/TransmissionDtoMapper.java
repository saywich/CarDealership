package ng.saywich.storageservice.application.contracts.mappers.parts;

import ng.saywich.storageservice.application.contracts.dtos.parts.TransmissionDto;
import ng.saywich.shared.domain.car.parts.Transmission;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransmissionDtoMapper {
    Transmission toTransmission(TransmissionDto dto);

    TransmissionDto toTransmissionDto(Transmission transmission);
}
