package ng.saywich.storageservice.application.contracts.assembling.operations;

import ng.saywich.storageservice.domain.assembling.AssembleRequest;

import java.util.List;
import java.util.UUID;

public final class QueryAssembleRequests {
    private QueryAssembleRequests() {}

    public record Request(UUID[] requestIds) {}

    public record Response(List<AssembleRequest> requests) {}
}
