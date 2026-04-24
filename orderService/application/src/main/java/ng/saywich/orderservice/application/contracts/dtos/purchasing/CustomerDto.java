package ng.saywich.orderservice.application.contracts.dtos.purchasing;

import java.util.UUID;

public record CustomerDto(
    UUID customerId,
    String name
) {
}
