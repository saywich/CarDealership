package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;

public final class UpdateAssembleRequest {
    private UpdateAssembleRequest() {}

    public record Request(AssembleRequest request) {}

    public record Response(AssembleRequest request) {}
}
