package ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Engine;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.EngineMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.EngineOptionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class EngineOptionMapper {
    @Autowired
    protected EngineMapper engineMapper;

    @Mapping(target = "optionId", source = "dao.id")
    @Mapping(target = "carPart", expression = "java(engineMapper.toEngine(dao.getEngine()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dao.getPriceDelta()))")
    public abstract CarOption<Engine> toCarOption(EngineOptionDao dao);

    @Mapping(target = "id", source = "option.optionId")
    @Mapping(target = "engine", expression = "java(engineMapper.toEngineDao(option.carPart()))")
    @Mapping(target = "priceDelta", expression = "java(option.priceDifference().value())")
    public abstract EngineOptionDao toOptionDao(CarOption<Engine> option);
}
