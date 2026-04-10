package ng.saywich.application.contracts.dtos.purchasing;

import java.util.UUID;

public record CustomerDto(
    UUID customerId,
    String name
) {
}
