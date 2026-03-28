package ng.saywich.presentation.dtos.purchasing;

import java.util.UUID;

public record ManagerDto(
    UUID managerId,
    String name
) { }
