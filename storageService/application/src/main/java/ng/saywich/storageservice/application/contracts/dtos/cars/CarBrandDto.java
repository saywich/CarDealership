package ng.saywich.storageservice.application.contracts.dtos.cars;

import java.util.UUID;

public record CarBrandDto(
    UUID id,
    String name
) { }
