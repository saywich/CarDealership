package ng.saywich.storageservice.application.contracts.dtos.parts;

import java.util.UUID;

public record InteriorDto(
    UUID partId,
    String name
) { }
