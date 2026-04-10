package ng.saywich.application.contracts.dtos.parts;

import java.util.UUID;

public record RimsDto(
    UUID partId,
    String name,
    int diameter
) { }
