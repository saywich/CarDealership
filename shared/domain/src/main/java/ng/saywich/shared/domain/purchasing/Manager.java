package ng.saywich.shared.domain.purchasing;

import java.util.UUID;

public record Manager (
    UUID managerId,
    String name
) {}
