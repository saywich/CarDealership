package ng.saywich.infrastructure.persistence.mappers;

import ng.saywich.domain.car.enums.FuelType;
import ng.saywich.domain.car.parts.Color;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.domain.value.HorsePower;
import ng.saywich.domain.value.Volume;
import ng.saywich.infrastructure.persistence.mappers.parts.ColorMapper;
import ng.saywich.infrastructure.persistence.mappers.parts.EngineMapper;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import ng.saywich.infrastructure.persistence.models.parts.EngineDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class EngineMapperTest {
    EngineMapper mapper = Mappers.getMapper(EngineMapper.class);

    @Test public void EngineMapsToEngineDao_DaoConstructs() {
        Engine engine = new Engine(
            UUID.randomUUID(),
            "V8",
            new HorsePower(128),
            new Volume(8.0),
            FuelType.PETROL
        );

        EngineDao dao = mapper.toEngineDao(engine);

        Assertions.assertEquals(engine.getPartId(), dao.getId());
        Assertions.assertEquals(engine.name(), dao.getName());
        Assertions.assertEquals(engine.power().value(), dao.getPower());
        Assertions.assertEquals(engine.volume().value(), dao.getVolume());
        Assertions.assertEquals(engine.fuelType(), dao.getFuelType());
    }

    @Test public void EngineDaoMapsToEngine_EngineConstructs() {
        EngineDao dao = EngineDao.builder()
            .id(UUID.randomUUID())
            .name("V8")
            .power(128)
            .volume(8.0)
            .fuelType(FuelType.PETROL)
            .build();

        Engine engine = mapper.toEngine(dao);

        Assertions.assertEquals(engine.getPartId(), dao.getId());
        Assertions.assertEquals(engine.name(), dao.getName());
        Assertions.assertEquals(engine.power().value(), dao.getPower());
        Assertions.assertEquals(engine.volume().value(), dao.getVolume());
        Assertions.assertEquals(engine.fuelType(), dao.getFuelType());
    }
}
