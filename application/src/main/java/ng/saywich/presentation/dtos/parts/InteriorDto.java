package ng.saywich.presentation.dtos.parts;

import java.util.UUID;

public record InteriorDto(
    UUID partId,
    String name
) { }
