package ng.saywich.domain.purchasing;

import java.util.UUID;

public record Manager (
    UUID managerId,
    String name
) {}
