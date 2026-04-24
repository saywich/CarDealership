package ng.saywich.storageservice.infrastructure.persistence.mappers.parts;

import ng.saywich.shared.domain.car.parts.Rims;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.RimsDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RimsMapper {
    @Mapping(target = "id", source = "rims.partId")
    public RimsDao toRimsDao(Rims rims);

    @Mapping(target = "partId", source = "dao.id")
    public Rims toRims(RimsDao dao);
}
