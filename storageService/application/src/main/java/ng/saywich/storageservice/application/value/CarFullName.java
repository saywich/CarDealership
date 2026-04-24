package ng.saywich.storageservice.application.value;

import java.util.Optional;

public record CarFullName(
    String brandName,
    Optional<String> modelName
) {}
