package ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Interior;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.InteriorMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.InteriorOptionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class InteriorOptionMapper {
    @Autowired
    protected InteriorMapper interiorMapper;

    @Mapping(target = "optionId", source = "dao.id")
    @Mapping(target = "carPart", expression = "java(interiorMapper.toInterior(dao.getInterior()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dao.getPriceDelta()))")
    public abstract CarOption<Interior> toCarOption(InteriorOptionDao dao);

    @Mapping(target = "id", source = "option.optionId")
    @Mapping(target = "interior", expression = "java(interiorMapper.toInteriorDao(option.carPart()))")
    @Mapping(target = "priceDelta", expression = "java(option.priceDifference().value())")
    public abstract InteriorOptionDao toOptionDao(CarOption<Interior> option);
}