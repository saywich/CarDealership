package ng.saywich.application.contracts.dtos.purchasing;

import java.util.UUID;

public record ManagerDto(
    UUID managerId,
    String name
) { }
