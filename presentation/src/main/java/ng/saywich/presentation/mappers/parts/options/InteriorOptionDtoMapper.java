package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Interior;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.InteriorDto;
import ng.saywich.presentation.mappers.parts.InteriorDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class InteriorOptionDtoMapper {

    @Autowired
    protected InteriorDtoMapper mapper;

    @Mapping(target = "carPart", expression = "java(mapper.toInterior(dto.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dto.priceDifference()))")
    public abstract CarOption<Interior> toOption(CarOptionDto<InteriorDto> dto);

    @Mapping(target = "carPart", expression = "java(mapper.toInteriorDto(option.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(option.priceDifference().value())")
    public abstract CarOptionDto<InteriorDto> toOptionDto(CarOption<Interior> option);
}