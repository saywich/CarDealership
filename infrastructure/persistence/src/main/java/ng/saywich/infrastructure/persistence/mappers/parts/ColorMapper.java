package ng.saywich.infrastructure.persistence.mappers.parts;

import ng.saywich.domain.car.parts.Color;
import ng.saywich.infrastructure.persistence.models.parts.ColorDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ColorMapper {
    @Mapping(target = "id", source = "color.partId")
    public ColorDao toColorDao(Color color);

    @Mapping(target = "partId", source = "dao.id")
    public Color toColor(ColorDao dao);
}
