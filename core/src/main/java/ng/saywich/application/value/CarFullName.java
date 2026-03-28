package ng.saywich.application.value;

import java.util.Optional;

public record CarFullName(
    String brandName,
    Optional<String> modelName
) {}
