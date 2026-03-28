package ng.saywich.infrastructure.persistence.mappers;

import ng.saywich.domain.car.CarBrand;
import ng.saywich.infrastructure.persistence.mappers.cars.CarBrandMapper;
import ng.saywich.infrastructure.persistence.models.cars.CarBrandDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class CarBrandMapperTest {
    CarBrandMapper mapper = Mappers.getMapper(CarBrandMapper.class);

    @Test public void CarBrandMapsToDao_DaoConstructs() {
        CarBrand domainBrand = new CarBrand(UUID.randomUUID(), "BMW");

        CarBrandDao dao = mapper.toCarBrandDao(domainBrand);

        Assertions.assertEquals(domainBrand.id(), dao.getId());
        Assertions.assertEquals(domainBrand.name(), dao.getName());
    }

    @Test public void CarBrandDaoMapsToCarBrand_CarBrandConstructs() {
        CarBrandDao dao = CarBrandDao.builder()
            .id(UUID.randomUUID())
            .name("BMW")
            .createdAt(Date.from(Instant.now()))
            .updatedAt(Date.from(Instant.now()))
            .removed(false)
            .build();

        CarBrand brand = mapper.toCarBrand(dao);

        Assertions.assertEquals(brand.id(), dao.getId());
        Assertions.assertEquals(brand.name(), dao.getName());
    }
}
