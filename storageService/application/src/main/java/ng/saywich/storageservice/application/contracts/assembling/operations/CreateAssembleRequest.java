package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;

public final class CreateAssembleRequest {
    private CreateAssembleRequest() {}

    public record Request(AssembleRequest request) {}

    public record Response(AssembleRequest request) {}
}
