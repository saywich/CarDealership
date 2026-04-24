package ng.saywich.storageservice.test.mappers;

import ng.saywich.shared.domain.car.parts.Interior;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.InteriorMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.InteriorDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class InteriorMapperTest {
    InteriorMapper mapper = Mappers.getMapper(InteriorMapper.class);

    @Test public void InteriorMapsToInteriorDao_DaoConstructs() {
        Interior interior = new Interior(UUID.randomUUID(), "leather");

        InteriorDao dao = mapper.toInteriorDao(interior);

        Assertions.assertEquals(interior.getPartId(), dao.getId());
        Assertions.assertEquals(interior.name(), dao.getName());
    }

    @Test public void InteriorDaoMapsToInterior_InteriorConstructs() {
        InteriorDao dao = InteriorDao.builder()
            .id(UUID.randomUUID())
            .name("leather")
            .build();

        Interior interior = mapper.toInterior(dao);

        Assertions.assertEquals(interior.getPartId(), dao.getId());
        Assertions.assertEquals(interior.name(), dao.getName());
    }
}
