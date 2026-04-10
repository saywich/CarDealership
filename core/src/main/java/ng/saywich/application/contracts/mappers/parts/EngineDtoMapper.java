package ng.saywich.application.contracts.mappers.parts;

import ng.saywich.domain.car.parts.Engine;
import ng.saywich.application.contracts.dtos.parts.EngineDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EngineDtoMapper {
    @Mapping(target = "power", expression = "java(new HorsePower(engineDto.power()))")
    @Mapping(target = "volume", expression = "java(new Volume(engineDto.volume()))")
    Engine toEngine(EngineDto engineDto);

    @Mapping(target = "power", expression = "java(engine.power().value())")
    @Mapping(target = "volume", expression = "java(engine.volume().value())")
    EngineDto toEngineDto(Engine engine);
}
