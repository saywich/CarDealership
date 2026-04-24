package ng.saywich.shared.domain.purchasing;

import java.util.UUID;

public record Customer(
    UUID customerId,
    String name
) {}
