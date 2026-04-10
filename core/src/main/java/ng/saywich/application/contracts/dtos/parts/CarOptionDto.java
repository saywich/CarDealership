package ng.saywich.application.contracts.dtos.parts;

import ng.saywich.domain.value.Price;

import java.math.BigDecimal;
import java.util.UUID;

public record CarOptionDto<T>(
    UUID optionId,
    T carPart,
    BigDecimal priceDifference
) { }
