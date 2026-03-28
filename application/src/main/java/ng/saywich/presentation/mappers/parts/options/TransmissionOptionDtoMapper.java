package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.Car;
import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.domain.value.Price;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.TransmissionDto;
import ng.saywich.presentation.mappers.parts.TransmissionDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

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