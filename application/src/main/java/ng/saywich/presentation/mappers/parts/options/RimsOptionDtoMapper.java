package ng.saywich.presentation.mappers.parts.options;

import ng.saywich.domain.car.CarOption;
import ng.saywich.domain.car.parts.Rims;
import ng.saywich.domain.value.Price;
import ng.saywich.presentation.dtos.parts.CarOptionDto;
import ng.saywich.presentation.dtos.parts.RimsDto;
import ng.saywich.presentation.mappers.parts.RimsDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class RimsOptionDtoMapper {
    @Autowired
    protected RimsDtoMapper mapper;

    public CarOption<Rims> toOption(CarOptionDto<RimsDto> dto) {
        return new CarOption<Rims>(
            dto.optionId(),
            mapper.toRims(dto.carPart()),
            new Price(dto.priceDifference())
        );
    }

    public CarOptionDto<RimsDto> toOptionDto(CarOption<Rims> option) {
        return new CarOptionDto<RimsDto>(
            option.optionId(),
            mapper.toRimsDto(option.carPart()),
            option.priceDifference().value()
        );
    }
}