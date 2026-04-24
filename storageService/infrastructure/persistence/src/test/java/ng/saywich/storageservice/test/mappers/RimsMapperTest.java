package ng.saywich.storageservice.test.mappers;

import ng.saywich.shared.domain.car.parts.Rims;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.RimsMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.RimsDao;
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
