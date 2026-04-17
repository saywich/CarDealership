package ng.saywich.infrastructure.persistence.mappers;

import ng.saywich.domain.car.parts.OptionSet;
import ng.saywich.infrastructure.persistence.OptionSetDaoMockRepository;
import ng.saywich.infrastructure.persistence.mappers.cars.CarBrandMapperImpl;
import ng.saywich.infrastructure.persistence.mappers.cars.OptionSetMapper;
import ng.saywich.infrastructure.persistence.mappers.cars.OptionSetMapperImpl;
import ng.saywich.infrastructure.persistence.mappers.parts.*;
import ng.saywich.infrastructure.persistence.mappers.parts.options.*;
import ng.saywich.infrastructure.persistence.models.cars.OptionSetDao;
import ng.saywich.infrastructure.persistence.models.parts.*;
import ng.saywich.infrastructure.persistence.models.parts.options.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
    OptionSetMapperImpl.class,
    CarBrandMapperImpl.class,
    EngineOptionMapperImpl.class,
    TransmissionOptionMapperImpl.class,
    RimsOptionMapperImpl.class,
    InteriorOptionMapperImpl.class,
    ColorOptionMapperImpl.class,
    BodyTypeOptionMapperImpl.class,

    EngineMapperImpl.class,
    TransmissionMapperImpl.class,
    RimsMapperImpl.class,
    InteriorMapperImpl.class,
    ColorMapperImpl.class,
})
public class OptionSetMapperTest {
    @Autowired
    OptionSetMapper mapper;

    @Test public void OptionSetDaoMapsToOptionSet_OptionSetConstructs() {
        OptionSetDao dao = new OptionSetDaoMockRepository().getOptionSet();

        OptionSet optionSet = mapper.toOptionSet(dao);

        Assertions.assertEquals(
            dao.getEngine().getEngine().getId(),
            optionSet.engine().carPart().getPartId()
        );
        Assertions.assertEquals(
            dao.getEngine().getEngine().getName(),
            optionSet.engine().carPart().name()
        );
        Assertions.assertEquals(
            dao.getEngine().getPriceDelta(),
            optionSet.engine().priceDifference().value()
        );

        Assertions.assertEquals(
            dao.getTransmission().getTransmission().getId(),
            optionSet.transmission().carPart().getPartId()
        );
        Assertions.assertEquals(
            dao.getTransmission().getTransmission().getName(),
            optionSet.transmission().carPart().name()
        );
        Assertions.assertEquals(
            dao.getTransmission().getPriceDelta(),
            optionSet.transmission().priceDifference().value()
        );

        Assertions.assertEquals(
            dao.getRims().getRims().getId(),
            optionSet.rims().carPart().getPartId()
        );
        Assertions.assertEquals(
            dao.getRims().getRims().getName(),
            optionSet.rims().carPart().name()
        );
        Assertions.assertEquals(
            dao.getRims().getPriceDelta(),
            optionSet.rims().priceDifference().value()
        );

        Assertions.assertEquals(
            dao.getInterior().getInterior().getId(),
            optionSet.interior().carPart().getPartId()
        );
        Assertions.assertEquals(
            dao.getInterior().getInterior().getName(),
            optionSet.interior().carPart().name()
        );
        Assertions.assertEquals(
            dao.getInterior().getPriceDelta(),
            optionSet.interior().priceDifference().value()
        );

        Assertions.assertEquals(
            dao.getColor().getColor().getId(),
            optionSet.color().carPart().getPartId()
        );
        Assertions.assertEquals(
            dao.getColor().getColor().getName(),
            optionSet.color().carPart().name()
        );
        Assertions.assertEquals(
            dao.getColor().getPriceDelta(),
            optionSet.color().priceDifference().value()
        );

        Assertions.assertEquals(
            dao.getBodyType().getBodyType(),
            optionSet.bodyType().carPart()
        );
        Assertions.assertEquals(
            dao.getBodyType().getPriceDelta(),
            optionSet.bodyType().priceDifference().value()
        );
    }
}
