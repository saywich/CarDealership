package ng.saywich.orderservice.application.contracts.dtos.carstock;

import java.math.BigDecimal;
import java.util.UUID;

public record StockCarDto(
    UUID carId,
    String vinNumber,
    UUID modelId,
    String modelName,
    UUID brandId,
    String brandName,
    BigDecimal basePrice
) { }
