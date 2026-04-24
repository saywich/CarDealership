package ng.saywich.storageservice.application.contracts.mappers.parts.options;

import ng.saywich.storageservice.application.contracts.dtos.parts.CarOptionDto;
import ng.saywich.storageservice.application.contracts.dtos.parts.RimsDto;
import ng.saywich.storageservice.application.contracts.mappers.parts.RimsDtoMapper;
import ng.saywich.shared.domain.car.CarOption;
import ng.saywich.shared.domain.car.parts.Rims;
import ng.saywich.shared.domain.value.Price;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

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