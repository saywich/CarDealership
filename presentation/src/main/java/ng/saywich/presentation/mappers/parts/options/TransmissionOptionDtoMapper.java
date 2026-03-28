package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.TransmissionDto;
import ng.saywich.presentation.mappers.parts.TransmissionDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TransmissionOptionDtoMapper {

    @Autowired
    protected TransmissionDtoMapper mapper;

    @Mapping(target = "carPart", expression = "java(mapper.toTransmission(dto.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dto.priceDifference()))")
    public abstract CarOption<Transmission> toOption(CarOptionDto<TransmissionDto> dto);

    @Mapping(target = "carPart", expression = "java(mapper.toTransmissionDto(option.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(option.priceDifference().value())")
    public abstract CarOptionDto<TransmissionDto> toOptionDto(CarOption<Transmission> option);
}