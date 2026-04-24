package ng.saywich.storageservice.application.contracts.mappers.parts.options;

import ng.saywich.storageservice.application.contracts.dtos.parts.CarOptionDto;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.enums.BodyType;
import ng.saywich.shared.domain.value.Price;
import org.mapstruct.Mapper;
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