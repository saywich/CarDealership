package ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Color;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.ColorMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.ColorOptionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class ColorOptionMapper {
    @Autowired
    protected ColorMapper colorMapper;

    @Mapping(target = "optionId", source = "dao.id")
    @Mapping(target = "carPart", expression = "java(colorMapper.toColor(dao.getColor()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dao.getPriceDelta()))")
    public abstract CarOption<Color> toCarOption(ColorOptionDao dao);

    @Mapping(target = "id", source = "option.optionId")
    @Mapping(target = "color", expression = "java(colorMapper.toColorDao(option.carPart()))")
    @Mapping(target = "priceDelta", expression = "java(option.priceDifference().value())")
    public abstract ColorOptionDao toOptionDao(CarOption<Color> option);
}
