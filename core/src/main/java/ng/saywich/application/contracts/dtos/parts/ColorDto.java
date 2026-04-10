package ng.saywich.application.contracts.dtos.parts;

import java.util.UUID;

public record ColorDto(
    UUID partId,
    String name,
    int hexCode
) { }
