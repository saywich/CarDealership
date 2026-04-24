package ng.saywich.orderservice.application.contracts.dtos.purchasing;

import java.util.UUID;

public record ManagerDto(
    UUID managerId,
    String name
) { }
