package ng.saywich.application.contracts.dtos.parts;

import ng.saywich.domain.car.enums.BodyType;

import java.util.UUID;

public record OptionSetDto(
    UUID optionSetId,
    CarOptionDto<EngineDto> engine,
    CarOptionDto<TransmissionDto> transmission,
    CarOptionDto<BodyType> bodyType,
    CarOptionDto<RimsDto> rims,
    CarOptionDto<InteriorDto> interior,
    CarOptionDto<ColorDto> color
) { }
