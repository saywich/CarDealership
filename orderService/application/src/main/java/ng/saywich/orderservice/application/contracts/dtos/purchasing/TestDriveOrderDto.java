package ng.saywich.orderservice.application.contracts.dtos.purchasing;

import java.util.Date;
import java.util.UUID;

public record TestDriveOrderDto(
    UUID orderId,
    UUID requestedCarId,
    CustomerDto customer,
    Date startDate
) { }
