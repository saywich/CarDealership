package ng.saywich.infrastructure.persistence.mappers.parts;

import ng.saywich.domain.car.parts.Interior;
import ng.saywich.infrastructure.persistence.models.parts.InteriorDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InteriorMapper {
    @Mapping(target = "id", source = "interior.partId")
    public InteriorDao toInteriorDao(Interior interior);

    @Mapping(target = "partId", source = "dao.id")
    public Interior toInterior(InteriorDao dao);
}
