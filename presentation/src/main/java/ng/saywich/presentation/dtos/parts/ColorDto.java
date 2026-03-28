package ng.saywich.presentation.dtos.parts;

import java.util.UUID;

public record ColorDto(
    UUID partId,
    String name,
    int hexCode
) { }
