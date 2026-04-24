package ng.saywich.storageservice.application.contracts.mappers.parts.options;

import ng.saywich.storageservice.application.contracts.dtos.parts.CarOptionDto;
import ng.saywich.storageservice.application.contracts.dtos.parts.EngineDto;
import ng.saywich.storageservice.application.contracts.mappers.parts.EngineDtoMapper;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Engine;
import ng.saywich.shared.domain.value.Price;
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
