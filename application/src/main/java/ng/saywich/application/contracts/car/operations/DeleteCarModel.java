package ng.saywich.application.contracts.car.operations;

import java.util.UUID;

public final class DeleteCarModel {
    private DeleteCarModel() {}

    public record Request(
        UUID modelId
    ) {}

    public record Response() {}
}