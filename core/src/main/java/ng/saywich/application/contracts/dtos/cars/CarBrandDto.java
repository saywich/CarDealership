package ng.saywich.application.contracts.dtos.cars;

import java.util.UUID;

public record CarBrandDto(
    UUID id,
    String name
) { }
