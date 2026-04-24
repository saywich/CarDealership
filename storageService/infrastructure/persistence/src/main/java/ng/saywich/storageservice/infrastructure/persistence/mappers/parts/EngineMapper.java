package ng.saywich.storageservice.infrastructure.persistence.mappers.parts;

import ng.saywich.shared.domain.car.parts.Engine;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.EngineDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EngineMapper {
    @Mapping(target = "id", expression = "java(engine.partId())")
    @Mapping(target = "power", expression = "java(engine.power().value())")
    @Mapping(target = "volume", expression = "java(engine.volume().value())")
    public EngineDao toEngineDao(Engine engine);

    @Mapping(target = "partId", expression = "java(dao.getId())")
    @Mapping(target = "power", expression = "java(new HorsePower(dao.getPower()))")
    @Mapping(target = "volume", expression = "java(new Volume(dao.getVolume()))")
    public Engine toEngine(EngineDao dao);
}

