package ng.saywich.presentation.mappers.cars;

import ng.saywich.domain.car.parts.OptionSet;
import ng.saywich.presentation.dtos.parts.OptionSetDto;
import ng.saywich.presentation.mappers.parts.options.*;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class OptionSetDtoMapper {
    @Autowired
    protected ColorOptionDtoMapper colorOptionMapper;

    @Autowired
    protected EngineOptionDtoMapper engineOptionMapper;

    @Autowired
    protected InteriorOptionDtoMapper interiorOptionMapper;

    @Autowired
    protected RimsOptionDtoMapper rimsOptionMapper;

    @Autowired
    protected TransmissionOptionDtoMapper transmissionOptionMapper;

    @Autowired
    protected BodyTypeOptionDtoMapper bodyTypeOptionMapper;

    public OptionSet toOptionSet(OptionSetDto dto) {
        return new OptionSet(
            dto.optionSetId(),
            engineOptionMapper.toOption(dto.engine()),
            transmissionOptionMapper.toOption(dto.transmission()),
            bodyTypeOptionMapper.toOption(dto.bodyType()),
            rimsOptionMapper.toOption(dto.rims()),
            interiorOptionMapper.toOption(dto.interior()),
            colorOptionMapper.toOption(dto.color())
        );
    }

    public OptionSetDto toOptionSetDto(OptionSet optionSet) {
        return new OptionSetDto(
            optionSet.optionSetId(),
            engineOptionMapper.toOptionDto(optionSet.engine()),
            transmissionOptionMapper.toOptionDto(optionSet.transmission()),
            bodyTypeOptionMapper.toOptionDto(optionSet.bodyType()),
            rimsOptionMapper.toOptionDto(optionSet.rims()),
            interiorOptionMapper.toOptionDto(optionSet.interior()),
            colorOptionMapper.toOptionDto(optionSet.color())
        );
    }
}
