package ng.saywich.presentation.dtos.cars;

import ng.saywich.presentation.dtos.parts.OptionSetDto;

import java.math.BigDecimal;
import java.util.UUID;

public record CarModelDto(
    UUID modelId,
    String name,
    CarBrandDto brand,
    OptionSetDto optionSet,
    BigDecimal basePrice
) { }
