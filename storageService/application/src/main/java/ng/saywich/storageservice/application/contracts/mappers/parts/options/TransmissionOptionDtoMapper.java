package ng.saywich.storageservice.application.contracts.mappers.parts.options;

import ng.saywich.storageservice.application.contracts.dtos.parts.CarOptionDto;
import ng.saywich.storageservice.application.contracts.dtos.parts.TransmissionDto;
import ng.saywich.storageservice.application.contracts.mappers.parts.TransmissionDtoMapper;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Transmission;
import ng.saywich.shared.domain.value.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TransmissionOptionDtoMapper {

    @Autowired
    protected TransmissionDtoMapper mapper;

    public CarOption<Transmission> toOption(CarOptionDto<TransmissionDto> dto) {
        return new CarOption<Transmission>(
            dto.optionId(),
            mapper.toTransmission(dto.carPart()),
            new Price(dto.priceDifference())
        );
    }

    public CarOptionDto<TransmissionDto> toOptionDto(CarOption<Transmission> option) {
        return new CarOptionDto<TransmissionDto>(
            option.optionId(),
            mapper.toTransmissionDto(option.carPart()),
            option.priceDifference().value()
        );
    }
}