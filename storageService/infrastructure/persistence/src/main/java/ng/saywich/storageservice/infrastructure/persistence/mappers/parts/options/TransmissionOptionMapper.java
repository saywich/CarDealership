package ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Transmission;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.TransmissionMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.TransmissionOptionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class TransmissionOptionMapper {
    @Autowired
    protected TransmissionMapper transmissionMapper;

    @Mapping(target = "optionId", source = "dao.id")
    @Mapping(target = "carPart", expression = "java(transmissionMapper.toTransmission(dao.getTransmission()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dao.getPriceDelta()))")
    public abstract CarOption<Transmission> toCarOption(TransmissionOptionDao dao);

    @Mapping(target = "id", source = "option.optionId")
    @Mapping(target = "transmission", expression = "java(transmissionMapper.toTransmissionDao(option.carPart()))")
    @Mapping(target = "priceDelta", expression = "java(option.priceDifference().value())")
    public abstract TransmissionOptionDao toOptionDao(CarOption<Transmission> option);
}
