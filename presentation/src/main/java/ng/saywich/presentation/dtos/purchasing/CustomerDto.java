package ng.saywich.presentation.dtos.purchasing;

import java.util.UUID;

public record CustomerDto(
    UUID customerId,
    String name
) {
}
