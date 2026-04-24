package ng.saywich.storageservice.application.contracts.dtos.cars;

import ng.saywich.storageservice.application.contracts.dtos.cars.CarBrandDto;
import ng.saywich.storageservice.application.contracts.dtos.parts.OptionSetDto;

import java.math.BigDecimal;
import java.util.UUID;

public record CarModelDto(
    UUID modelId,
    String name,
    CarBrandDto brand,
    OptionSetDto optionSet,
    BigDecimal basePrice
) { }
