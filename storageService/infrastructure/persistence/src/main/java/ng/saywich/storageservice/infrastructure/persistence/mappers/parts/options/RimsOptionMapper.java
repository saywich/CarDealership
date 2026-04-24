package ng.saywich.storageservice.infrastructure.persistence.mappers.parts.options;

import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Rims;
import ng.saywich.storageservice.infrastructure.persistence.mappers.parts.RimsMapper;
import ng.saywich.storageservice.infrastructure.persistence.models.parts.options.RimsOptionDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RimsOptionMapper {
    @Autowired
    protected RimsMapper rimsMapper;

    @Mapping(target = "optionId", source = "dao.id")
    @Mapping(target = "carPart", expression = "java(rimsMapper.toRims(dao.getRims()))")
    @Mapping(target = "priceDifference", expression = "java(new Price(dao.getPriceDelta()))")
    public abstract CarOption<Rims> toCarOption(RimsOptionDao dao);

    @Mapping(target = "id", source = "option.optionId")
    @Mapping(target = "rims", expression = "java(rimsMapper.toRimsDao(option.carPart()))")
    @Mapping(target = "priceDelta", expression = "java(option.priceDifference().value())")
    public abstract RimsOptionDao toOptionDao(CarOption<Rims> option);
}