package ng.saywich.infrastructure.persistence.mappers;

import ng.saywich.domain.car.parts.Color;
import ng.saywich.domain.car.parts.Engine;
import ng.saywich.domain.car.parts.Interior;
import ng.saywich.domain.car.parts.Rims;
import ng.saywich.infrastructure.persistence.mappers.parts.ColorMapper;
import ng.saywich.infrastructure.persistence.mappers.parts.InteriorMapper;
import ng.saywich.infrastructure.persistence.mappers.parts.RimsMapper;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import ng.saywich.infrastructure.persistence.models.parts.EngineDao;
import ng.saywich.infrastructure.persistence.models.parts.InteriorDao;
import ng.saywich.infrastructure.persistence.models.parts.RimsDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class RimsMapperTest {
    RimsMapper mapper = Mappers.getMapper(RimsMapper.class);

    @Test public void RimsMapsToRimsDao_DaoConstructs() {
        Rims rims = new Rims(UUID.randomUUID(), "BBS", 21);

        RimsDao dao = mapper.toRimsDao(rims);

        Assertions.assertEquals(rims.getPartId(), dao.getId());
        Assertions.assertEquals(rims.name(), dao.getName());
        Assertions.assertEquals(rims.diameter(), dao.getDiameter());
    }

    @Test public void RimsDaoMapsToRims_RimsConstructs() {
        RimsDao dao = RimsDao.builder()
            .id(UUID.randomUUID())
            .name("BBS")
            .diameter(21)
            .build();

        Rims rims = mapper.toRims(dao);

        Assertions.assertEquals(rims.getPartId(), dao.getId());
        Assertions.assertEquals(rims.name(), dao.getName());
        Assertions.assertEquals(rims.diameter(), dao.getDiameter());
    }
}
