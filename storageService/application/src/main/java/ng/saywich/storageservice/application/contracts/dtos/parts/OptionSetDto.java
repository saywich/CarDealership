package ng.saywich.storageservice.application.contracts.dtos.parts;

import ng.saywich.storageservice.application.contracts.dtos.parts.InteriorDto;
import ng.saywich.storageservice.application.contracts.dtos.parts.TransmissionDto;
import ng.saywich.shared.domain.car.enums.BodyType;

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
