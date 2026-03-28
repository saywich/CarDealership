package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.domain.value.Price;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.EngineDto;
import ng.saywich.presentation.mappers.parts.EngineDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EngineOptionDtoMapper {
    @Autowired
    protected EngineDtoMapper mapper;

    public CarOption<Engine> toOption(CarOptionDto<EngineDto> dto) {
        return new CarOption<Engine>(
            dto.optionId(),
            mapper.toEngine(dto.carPart()),
            new Price(dto.priceDifference())
        );
    }

    public CarOptionDto<EngineDto> toOptionDto(CarOption<Engine> option) {
        return new CarOptionDto<EngineDto>(
            option.optionId(),
            mapper.toEngineDto(option.carPart()),
            option.priceDifference().value()
        );
    }
}
