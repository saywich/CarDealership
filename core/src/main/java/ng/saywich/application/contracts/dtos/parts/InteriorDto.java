package ng.saywich.application.contracts.dtos.parts;

import java.util.UUID;

public record InteriorDto(
    UUID partId,
    String name
) { }
