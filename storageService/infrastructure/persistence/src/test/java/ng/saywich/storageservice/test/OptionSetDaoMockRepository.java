package ng.saywich.storageservice.test;

import ng.saywich.shared.domain.car.enums.BodyType;
import ng.saywich.shared.domain.car.enums.DriveTrain;
import ng.saywich.shared.domain.car.enums.FuelType;
import ng.saywich.shared.domain.car.enums.TransmissionType;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.OptionSetDao;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.*;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class OptionSetDaoMockRepository {
    public OptionSetDaoMockRepository() {}

    public OptionSetDao getOptionSet() {
        return OptionSetDao.builder()
            .id(UUID.randomUUID())
            .engine(EngineOptionDao.builder()
                .id(UUID.randomUUID())
                .engine(EngineDao.builder()
                    .id(UUID.randomUUID())
                    .name("V8 Turbo")
                    .power(450)
                    .volume(4.0)
                    .fuelType(FuelType.PETROL)
                    .createdAt(Date.from(Instant.now()))
                    .updatedAt(Date.from(Instant.now()))
                    .removed(false)
                    .build())
                .priceDelta(BigDecimal.valueOf(3000))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .removed(false)
                .build())
            .transmission(TransmissionOptionDao.builder()
                .id(UUID.randomUUID())
                .transmission(TransmissionDao.builder()
                    .id(UUID.randomUUID())
                    .name("8AT")
                    .gearsCount(8)
                    .type(TransmissionType.AUTOMATIC)
                    .driveTrain(DriveTrain.ALL)
                    .createdAt(Date.from(Instant.now()))
                    .updatedAt(Date.from(Instant.now()))
                    .removed(false)
                    .build())
                .priceDelta(BigDecimal.valueOf(2000))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .removed(false)
                .build())
            .rims(RimsOptionDao.builder()
                .id(UUID.randomUUID())
                .rims(RimsDao.builder()
                    .id(UUID.randomUUID())
                    .name("Sport Rims")
                    .diameter(20)
                    .createdAt(Date.from(Instant.now()))
                    .updatedAt(Date.from(Instant.now()))
                    .removed(false)
                    .build())
                .priceDelta(BigDecimal.valueOf(1200))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .removed(false)
                .build())
            .interior(InteriorOptionDao.builder()
                .id(UUID.randomUUID())
                .interior(InteriorDao.builder()
                    .id(UUID.randomUUID())
                    .name("Leather Black")
                    .createdAt(Date.from(Instant.now()))
                    .updatedAt(Date.from(Instant.now()))
                    .removed(false)
                    .build())
                .priceDelta(BigDecimal.valueOf(1800))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .removed(false)
                .build())
            .color(ColorOptionDao.builder()
                .id(UUID.randomUUID())
                .color(ColorDao.builder()
                    .id(UUID.randomUUID())
                    .name("Midnight Blue")
                    .hexCode(0x191970)
                    .createdAt(Date.from(Instant.now()))
                    .updatedAt(Date.from(Instant.now()))
                    .removed(false)
                    .build())
                .priceDelta(BigDecimal.valueOf(700))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .removed(false)
                .build())
            .bodyType(BodyTypeOptionDao.builder()
                .id(UUID.randomUUID())
                .bodyType(BodyType.SUV)
                .priceDelta(BigDecimal.valueOf(0))
                .createdAt(Date.from(Instant.now()))
                .updatedAt(Date.from(Instant.now()))
                .removed(false)
                .build())
            .createdAt(Date.from(Instant.now()))
            .updatedAt(Date.from(Instant.now()))
            .removed(false)
            .build();
    }
}
