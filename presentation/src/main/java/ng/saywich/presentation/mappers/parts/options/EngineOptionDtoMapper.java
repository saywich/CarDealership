package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.EngineDto;
import ng.saywich.presentation.mappers.parts.EngineDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EngineOptionDtoMapper {
    @Autowired
    protected EngineDtoMapper mapper;

    @Mapping(target = "carPart", expression = "java(mapper.toEngine(dto.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dto.priceDifference()))")
    public abstract CarOption<Engine> toOption(CarOptionDto<EngineDto> dto);

    @Mapping(target = "carPart", expression = "java(mapper.toEngineDto(option.carPart()))")
    @Mapping(target = "priceDifference", expression = "java(option.priceDifference().value())")
    public abstract CarOptionDto<EngineDto> toOptionDto(CarOption<Engine> option);
}
