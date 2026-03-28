package ng.saywich.infrastructure.persistence.mappers;

import ng.saywich.domain.car.enums.DriveTrain;
import ng.saywich.domain.car.enums.TransmissionType;
import ng.saywich.domain.car.parts.*;
import ng.saywich.infrastructure.persistence.mappers.parts.ColorMapper;
import ng.saywich.infrastructure.persistence.mappers.parts.InteriorMapper;
import ng.saywich.infrastructure.persistence.mappers.parts.RimsMapper;
import ng.saywich.infrastructure.persistence.mappers.parts.TransmissionMapper;
import ng.saywich.infrastructure.persistence.models.parts.*;
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
