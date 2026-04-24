package ng.saywich.storageservice.application.contracts.dtos.parts;

import java.math.BigDecimal;
import java.util.UUID;

public record CarOptionDto<T>(
    UUID optionId,
    T carPart,
    BigDecimal priceDifference
) { }
