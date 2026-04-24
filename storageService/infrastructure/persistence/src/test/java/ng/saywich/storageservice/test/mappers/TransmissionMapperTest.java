package ng.saywich.storageservice.test.mappers;

import ng.saywich.shared.domain.car.enums.DriveTrain;
import ng.saywich.shared.domain.car.enums.TransmissionType;
import ng.saywich.shared.domain.car.parts.Transmission;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.TransmissionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.TransmissionDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

public class TransmissionMapperTest {
    TransmissionMapper mapper = Mappers.getMapper(TransmissionMapper.class);

    @Test public void TransmissionMapsToTransmissionDao_DaoConstructs() {
        Transmission transmission = new Transmission(
            UUID.randomUUID(),
            "DSG",
            7,
            TransmissionType.AUTOMATIC,
            DriveTrain.ALL
        );

        TransmissionDao dao = mapper.toTransmissionDao(transmission);

        Assertions.assertEquals(transmission.getPartId(), dao.getId());
        Assertions.assertEquals(transmission.name(), dao.getName());
        Assertions.assertEquals(transmission.gearsCount(), dao.getGearsCount());
        Assertions.assertEquals(transmission.type(), dao.getType());
        Assertions.assertEquals(transmission.driveTrain(), dao.getDriveTrain());
    }

    @Test public void TransmissionDaoMapsToTransmission_TransmissionConstructs() {
        TransmissionDao dao = TransmissionDao.builder()
            .id(UUID.randomUUID())
            .name("DSG")
            .gearsCount(7)
            .type(TransmissionType.AUTOMATIC)
            .driveTrain(DriveTrain.ALL)
            .build();

        Transmission transmission = mapper.toTransmission(dao);

        Assertions.assertEquals(transmission.getPartId(), dao.getId());
        Assertions.assertEquals(transmission.name(), dao.getName());
        Assertions.assertEquals(transmission.gearsCount(), dao.getGearsCount());
        Assertions.assertEquals(transmission.type(), dao.getType());
        Assertions.assertEquals(transmission.driveTrain(), dao.getDriveTrain());
    }
}
