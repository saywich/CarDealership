package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Rims;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.RimsDto;
import ng.saywich.presentation.mappers.parts.RimsDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RimsOptionDtoMapper {

    @Autowired
    protected RimsDtoMapper mapper;

    @Mapping(target = "carPart", expression = "java(mapper.toRims(dto.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dto.priceDifference()))")
    public abstract CarOption<Rims> toOption(CarOptionDto<RimsDto> dto);

    @Mapping(target = "carPart", expression = "java(mapper.toRimsDto(option.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(option.priceDifference().value())")
    public abstract CarOptionDto<RimsDto> toOptionDto(CarOption<Rims> option);
}