package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.enums.BodyType;
import ng.saywich.domain.car.parts.ICarPart;
import ng.saywich.domain.value.Price;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BodyTypeOptionDtoMapper {
    public CarOption<BodyType> toOption(CarOptionDto<BodyType> dto) {
        return new CarOption<BodyType>(dto.optionId(), dto.carPart(), new Price(dto.priceDifference()));
    }

    public CarOptionDto<BodyType> toOptionDto(CarOption<BodyType> option) {
        return new CarOptionDto<BodyType>(
            option.optionId(), option.carPart(), option.priceDifference().value());
    }
}