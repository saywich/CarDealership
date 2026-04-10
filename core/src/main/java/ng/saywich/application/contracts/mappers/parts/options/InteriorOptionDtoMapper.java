package ng.saywich.application.contracts.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Interior;
import ng.saywich.domain.value.Price;
import ng.saywich.application.contracts.dtos.parts.CarOptionDto;
import ng.saywich.application.contracts.dtos.parts.InteriorDto;
import ng.saywich.application.contracts.mappers.parts.InteriorDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class InteriorOptionDtoMapper {
    @Autowired
    protected InteriorDtoMapper mapper;

    public CarOption<Interior> toOption(CarOptionDto<InteriorDto> dto) {
        return new CarOption<Interior>(
            dto.optionId(),
            mapper.toInterior(dto.carPart()),
            new Price(dto.priceDifference())
        );
    }

    public CarOptionDto<InteriorDto> toOptionDto(CarOption<Interior> option) {
        return new CarOptionDto<InteriorDto>(
            option.optionId(),
            mapper.toInteriorDto(option.carPart()),
            option.priceDifference().value()
        );
    }
}