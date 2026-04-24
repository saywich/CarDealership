package ng.saywich.storageservice.infrastructure.persistence.mappers.cars;

import ng.saywich.shared.domain.car.parts.OptionSet;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options.*;
import ng.saywich.storageservice.infrastructure.persistence.models.cars.OptionSetDao;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class OptionSetMapper {
    @Autowired
    protected ColorOptionMapper colorOptionMapper;

    @Autowired
    protected EngineOptionMapper engineOptionMapper;

    @Autowired
    protected InteriorOptionMapper interiorOptionMapper;

    @Autowired
    protected RimsOptionMapper rimsOptionMapper;

    @Autowired
    protected TransmissionOptionMapper transmissionOptionMapper;

    @Autowired
    protected BodyTypeOptionMapper bodyTypeOptionMapper;

    public OptionSetDao toOptionSetDao(OptionSet optionSet) {
        return OptionSetDao.builder()
            .id(optionSet.optionSetId())
            .engine(engineOptionMapper.toOptionDao(optionSet.engine()))
            .transmission(transmissionOptionMapper.toOptionDao(optionSet.transmission()))
            .rims(rimsOptionMapper.toOptionDao(optionSet.rims()))
            .bodyType(bodyTypeOptionMapper.toOptionDao(optionSet.bodyType()))
            .color(colorOptionMapper.toOptionDao(optionSet.color()))
            .interior(interiorOptionMapper.toOptionDao(optionSet.interior()))
            .build();
    }

    public OptionSet toOptionSet(OptionSetDao dao) {
        OptionSet.OptionSetBuilder builder = OptionSet.builder();

        builder.optionSetId(dao.getId());
        builder.engine(engineOptionMapper.toCarOption(dao.getEngine()));
        builder.transmission(transmissionOptionMapper.toCarOption(dao.getTransmission()));
        builder.bodyType(bodyTypeOptionMapper.toCarOption(dao.getBodyType()));
        builder.rims(rimsOptionMapper.toCarOption(dao.getRims()));
        builder.interior(interiorOptionMapper.toCarOption(dao.getInterior()));
        builder.color(colorOptionMapper.toCarOption(dao.getColor()));

        return builder.build();
    }
}
