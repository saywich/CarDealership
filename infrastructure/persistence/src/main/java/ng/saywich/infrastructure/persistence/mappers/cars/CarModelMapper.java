package ng.saywich.infrastructure.persistence.mappers.cars;

import ng.saywich.domain.car.CarModel;
import ng.saywich.domain.value.Price;
import ng.saywich.infrastructure.persistence.models.cars.CarModelDao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    imports = Price.class
)
public abstract class CarModelMapper {
    @Autowired
    protected OptionSetMapper optionSetMapper;

    @Autowired
    protected CarBrandMapper carBrandMapper;

    @Mapping(target = "modelId", source = "dao.id")
    @Mapping(target = "brand", expression = "java(carBrandMapper.toCarBrand(dao.getBrand()))")
    @Mapping(
        target = "optionSet",
        expression = "java(optionSetMapper.toOptionSet(dao.getDefaultOptionSet()))"
    )
    @Mapping(target = "basePrice", expression = "java(new Price(dao.getPrice()))")
    public abstract CarModel toCarModel(CarModelDao dao);

    @Mapping(target = "id", source = "carModel.modelId")
    @Mapping(target = "brand", expression = "java(carBrandMapper.toCarBrandDao(carModel.getBrand()))")
    @Mapping(
        target = "defaultOptionSet",
        expression = "java(optionSetMapper.toOptionSetDao(carModel.getOptionSet()))"
    )
    @Mapping(target = "price", expression = "java(carModel.getBasePrice().value())")
    public abstract CarModelDao toCarModelDao(CarModel carModel);

}
