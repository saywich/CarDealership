package ng.saywich.infrastructure.persistence.mappers.parts;

import ng.saywich.domain.car.parts.Transmission;
import ng.saywich.infrastructure.persistence.models.parts.TransmissionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TransmissionMapper {
    @Mapping(target = "id", source = "transmission.partId")
    public TransmissionDao toTransmissionDao(Transmission transmission);

    @Mapping(target = "partId", source = "dao.id")
    public Transmission toTransmission(TransmissionDao dao);
}
