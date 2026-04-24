package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;

import java.util.UUID;

public final class DeleteAssembleRequest {
    private DeleteAssembleRequest() {}

    public record Request(UUID requestId) {}

    public record Response(AssembleRequest request) {}
}
