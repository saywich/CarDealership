package ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.enums.BodyType;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.BodyTypeOptionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BodyTypeOptionMapper {
    @Mapping(target = "optionId", source = "dao.id")
    @Mapping(target = "carPart", expression = "java(dao.getBodyType())")
    @Mapping(target = "priceDifference", expression = "java(new Price(dao.getPriceDelta()))")
    public abstract CarOption<BodyType> toCarOption(BodyTypeOptionDao dao);

    @Mapping(target = "id", source = "option.optionId")
    @Mapping(target = "bodyType", expression = "java(option.carPart())")
    @Mapping(target = "priceDelta", expression = "java(option.priceDifference().value())")
    public abstract BodyTypeOptionDao toOptionDao(CarOption<BodyType> option);
}